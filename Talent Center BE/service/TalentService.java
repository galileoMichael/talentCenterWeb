package com.tujuhsembilan.talentcenter.service;

import com.tujuhsembilan.talentcenter.dto.request.*;
import com.tujuhsembilan.talentcenter.dto.response.*;
import com.tujuhsembilan.talentcenter.model.*;
import com.tujuhsembilan.talentcenter.repository.*;
import com.tujuhsembilan.talentcenter.response.MessageResponse;
import com.tujuhsembilan.talentcenter.service.method.TalentListMethod;

import jakarta.transaction.Transactional;
import lib.i18n.utility.MessageUtil;
import lib.minio.MinioSrvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TalentService {
    @Autowired
    TalentRepository talentRepository;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    MessageSource messageSource;

    @Autowired
    TalentPositionRepository talentPositionRepository;

    @Autowired
    TalentSkillsetRepository talentSkillsetRepository;

    @Autowired 
    TalentWishlistRepository talentWishlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MinioSrvc minioService;


    public ResponseEntity<Object> getTalents(Pageable page, TalentFilterDto talentFilterDTO) {
        ResponseBodyDto result = new ResponseBodyDto();
        HttpStatus status = HttpStatus.OK;
        String message = "";
        TalentListMethod talentListMethod = new TalentListMethod();

        try{
            talentListMethod.filterTalent(talentRepository, result, status, page, 
                    talentFilterDTO, 
                    minioService, 
                    messageUtil);

        } catch (Exception e) {
            e.printStackTrace();
            message = "Error getting talents";
            result.setMessage(message);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(result);
    }

    private String getImageURL(String filename) {
        String url = "";

        if (filename != null) {
            url = minioService.getPublicLink(filename);
        }

        return url;
    }

    @Transactional
    public MessageResponse addToList(AddToListRequest addToListRequest) {
        try {
            Optional<User> user = userRepository.findById(addToListRequest.getUserId());
            User user1 = user.get();
            log.info("ini data user: {}", user1);
            Client clientId = user1.getClient();
            String talentName = talentRepository.findById(addToListRequest.getTalentId()).get().getTalentName();

            TalentWishlist talentWishlist = TalentWishlist.builder()
                    .talent(talentRepository.findById(addToListRequest.getTalentId()).get())
                    .client(clientId)
                    .wishlistDate(new Timestamp(System.currentTimeMillis()))
                    .isActive(true)
                    .build();
            talentWishlistRepository.save(talentWishlist);
            return new MessageResponse(
                    messageUtil.get("application.success.add.talent-to-list", talentName),
                    HttpStatus.OK.value(), "OK");
        }catch (Exception e) {
            return new MessageResponse(messageUtil.get("application.error.data-not-found"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR");
        }
    }

    public String crateDownloadCV(UUID talentId) {
        Optional<Talent> talent = talentRepository.findById(talentId);
        if(!talent.isPresent()) {
            return null;
        }
        String urlCV = talent.get().getTalentCVUrl();
        if (urlCV == null || urlCV.isEmpty()) {
            return null;
        }
        try {
            return minioService.getPublicLink(urlCV);
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Error Getting CV";
            return message;
        }
    }

    public ResponseEntity<Object> postDownloadCV(DownloadCvRequest request){
        String resource = crateDownloadCV(request.getTalentId());
        log.info("Download CV response: {}", resource);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource + "\"")
                .body(resource);
    }

    public ResponseEntity<Object> getTalentById(UUID talentId) {
        Optional<Talent> talent = talentRepository.findById(talentId);
        if(!talent.isPresent()) {
            return new ResponseEntity<>(messageUtil.get("application.error.data-not-found"),
                    HttpStatus.NOT_FOUND);
        }
        TalentByIDDto talentByIdDTO = new TalentByIDDto(
                talent.get().getTalentId(),
                getImageURL(talent.get().getTalentPhotoFilename()),
                talent.get().getTalentName(),
                talent.get().getTalentStatus() != null
                        ? talent.get().getTalentStatus().getTalentStatusName()
                        : null,
                talent.get().getEmployeeNumber(),
                talent.get().getBirthDate(),
                talent.get().getTalentDescription(),
                talent.get().getTalentCvFilename(),
                talent.get().getExperience(),
                talent.get().getTalentLevel() != null
                        ? talent.get().getTalentLevel().getTalentLevelName()
                        : null,
                talent.get().getTotalProjectCompleted() != null
                        ? talent.get().getTotalProjectCompleted()
                        : 0,
                talent.get().getTotalProjectCompleted() != null
                        ? talent.get().getTotalProjectCompleted()
                        : 0,
                talentPositionRepository.findByTalentId(talentId)
                        .stream()
                        .map(talentPosition -> new PositionDto(
                                talentPosition.getPosition().getPositionId(),
                                talentPosition.getPosition().getPositionName()))
                        .collect(Collectors.toList()),
                talentSkillsetRepository.findByIdTalentId(talentId)
                        .stream()
                        .map(talentSkillset -> new SkillsetDto(
                                talentSkillset.getSkillset().getSkillsetId(),
                                talentSkillset.getSkillset().getSkillsetName()))
                        .collect(Collectors.toList()),
                talent.get().getEmail(),
                talent.get().getCellphone(),
                talent.get().getEmployeeStatus() != null
                        ? talent.get().getEmployeeStatus().getEmployeeStatusName()
                        : null,
                talent.get().getTalentAvailability(),
                talent.get().getBiographyVideoUrl());
        return new ResponseEntity<>(talentByIdDTO, HttpStatus.OK);
    }

}
