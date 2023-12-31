package com.manjot.ElysianTravels.dto.auth.request;

import jakarta.validation.constraints.NotBlank;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}