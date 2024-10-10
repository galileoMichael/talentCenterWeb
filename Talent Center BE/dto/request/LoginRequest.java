package com.tujuhsembilan.talentcenter.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {

    @NotNull(message = "{email.required}")
    @Size(max = 100)
    private String email;

    @NotNull(message = "{password.required}")
    @Size(max = 50, message = "{password.length}")
    private String password;
}
