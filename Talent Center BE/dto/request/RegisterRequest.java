package com.tujuhsembilan.talentcenter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {

    @NotBlank(message = "{email.required}")
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, message = "{password.length}")
    private String password;
}
