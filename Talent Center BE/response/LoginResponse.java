package com.tujuhsembilan.talentcenter.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    private String message;
    private int statusCode;
    private String status;
    private UserData data;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserData {
        private String email;
        private UUID userId;
        private String token;
    }
}
