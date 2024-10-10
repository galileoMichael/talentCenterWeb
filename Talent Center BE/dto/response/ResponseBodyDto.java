package com.tujuhsembilan.talentcenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBodyDto {
    private long total;
    private Object data;
    private String message;
    private int statusCode;
    private String status;
}
