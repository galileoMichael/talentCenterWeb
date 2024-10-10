package com.tujuhsembilan.talentcenter.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToListRequest {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID talentId;
}
