package com.tujuhsembilan.talentcenter.service;

import com.tujuhsembilan.talentcenter.dto.response.PositionDto;
import com.tujuhsembilan.talentcenter.dto.response.TalentExperienceDto;
import com.tujuhsembilan.talentcenter.model.*;
import com.tujuhsembilan.talentcenter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MasterService {

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SkillsetRepository skillsetRepository;

    @Autowired
    private SkillsetTypeRepository skillsetTypeRepository;

    @Autowired
    private TalentLevelRepository talentLevelRepository;


    public List<PositionDto> getPositionTalent(){
        List<PositionDto> positionDtoList = new ArrayList<>();
        List<Position> positionList = positionRepository.findAll();
        for(Position position : positionList) {
            PositionDto responsePositionDto = new PositionDto();
            responsePositionDto.setPositionId(position.getPositionId());
            responsePositionDto.setPositionName(position.getPositionName());
            positionDtoList.add(responsePositionDto);
        }
        return positionDtoList;
    }

    public List<TalentLevel> getTalentLevel(){
        return talentLevelRepository.findAll();
    }

    public ResponseEntity<Object> getTalentExperience(Pageable pageable){
        Page<Talent> talentPage = talentRepository.findAll(pageable);
        List<TalentExperienceDto> talentExperienceDtoS = talentPage.getContent().stream()
                .map(talent -> new TalentExperienceDto(talent.getExperience()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(talentExperienceDtoS, HttpStatus.OK);
    }

    public List<SkillsetType> findSkillsetBySkillsetTypeName(String skillsetTypeName) {
        return skillsetTypeRepository.findBySkillsetTypeName(skillsetTypeName);
    }

    public List<Skillset> findSkillsetByTypeId(UUID skillsetTypeId) {
        return skillsetRepository.findBySkillsetTypeId(skillsetTypeId);
    }

    public List<Skillset> getAllSkillsets() {
        return skillsetRepository.findAll();
    }


}
