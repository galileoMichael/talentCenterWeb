package com.tujuhsembilan.talentcenter.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TalentFilterDto {

    private String skillset;
    private String position;
    private String level;
    private Boolean availability;
    private String experience;

}
