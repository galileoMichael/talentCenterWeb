package com.tujuhsembilan.talentcenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalentLevelDto {
    private UUID talentLevelId;
    private String talentLevelName;
}
