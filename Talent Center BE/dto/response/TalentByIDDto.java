package com.tujuhsembilan.talentcenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalentByIDDto {
    private UUID talentId;
    private String talentPhoto;
    private String talentName;
    private String talentStatus;
    private String nip;
    private Timestamp dob;
    private String talentDescription;
    private String cv;
    private int talentExperience;
    private String talentLevel;
    private Integer projectCompleted;
    private Integer totalRequest;
    private List<PositionDto> position;
    private List<SkillsetDto> skillset;
    private String email;
    private String cellphone;
    private String employeeStatus;
    private boolean talentAvailability;
    private String videoUrl;

}
