package com.tujuhsembilan.talentcenter.controller;

import com.tujuhsembilan.talentcenter.dto.response.ListResponse;
import com.tujuhsembilan.talentcenter.dto.response.PositionDto;
import com.tujuhsembilan.talentcenter.dto.response.SkillsetDto;
import com.tujuhsembilan.talentcenter.dto.response.TalentLevelDto;
import com.tujuhsembilan.talentcenter.model.Skillset;
import com.tujuhsembilan.talentcenter.model.SkillsetType;
import com.tujuhsembilan.talentcenter.service.MasterService;
import lib.i18n.utility.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/master-management")
public class MasterController {
    @Autowired
    MasterService masterService;

    @Autowired
    MessageUtil messageUtil;

    @GetMapping("/talent-position-option-lists")
    public ResponseEntity<ListResponse<PositionDto>> getAllTalentPosition(){
        List<PositionDto> listPositionTalent = masterService.getPositionTalent().stream()
                .map(pos -> new PositionDto(pos.getPositionId(), pos.getPositionName()))
                .collect(Collectors.toList());
        ListResponse<PositionDto> response = new ListResponse<>(listPositionTalent,
                messageUtil.get("application.success.get"), HttpStatus.OK.value(), "Success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/skill-set-option-lists")
    public ResponseEntity<ListResponse<SkillsetDto>> getSearchSkillsetByType(
            @RequestParam("type") String skillsetTypeName) {
        List<SkillsetDto> skillsetDtos;

        if (skillsetTypeName.equalsIgnoreCase("All")) {
            skillsetDtos = masterService.getAllSkillsets().stream()
                    .map(skillset -> new SkillsetDto(skillset.getSkillsetId(), skillset.getSkillsetName()))
                    .collect(Collectors.toList());
        } else {
            List<SkillsetType> skillsetTypeList = masterService.findSkillsetBySkillsetTypeName(skillsetTypeName);
            UUID skillsetTypeId = null;
            if (!skillsetTypeList.isEmpty()) {
                skillsetTypeId = skillsetTypeList.get(0).getSkillsetTypeId();
            }

            List<Skillset> skillsetList = masterService.findSkillsetByTypeId(skillsetTypeId);

            skillsetDtos = skillsetList.stream()
                    .map(skillset -> new SkillsetDto(skillset.getSkillsetId(), skillset.getSkillsetName()))
                    .collect(Collectors.toList());
        }

        ListResponse<SkillsetDto> response = new ListResponse<>(skillsetDtos,
                messageUtil.get("application.success.get"), HttpStatus.OK.value(), "Success");

        return ResponseEntity.ok(response);

    }

    @GetMapping("/year-experience-option-lists")
    public ResponseEntity<Object> getAllExperience(@PageableDefault(page = 0, size = 8)Pageable pageable) {
        return masterService.getTalentExperience(pageable);
    }

    @GetMapping("/talent-level-option-lists")
    public ResponseEntity<ListResponse<TalentLevelDto>> getTalentLevel() {
        List<TalentLevelDto> talentLevelDtoS = masterService.getTalentLevel().stream()
                .map(level -> new TalentLevelDto(level.getTalentLevelId(), level.getTalentLevelName()))
                .collect(Collectors.toList());

        ListResponse<TalentLevelDto> listResponse = new ListResponse<>(talentLevelDtoS,
                messageUtil.get("application.success.get"), HttpStatus.OK.value(), "Success");
        return ResponseEntity.ok(listResponse);
    }
}
