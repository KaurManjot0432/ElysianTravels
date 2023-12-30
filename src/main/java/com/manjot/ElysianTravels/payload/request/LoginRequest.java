package com.manjot.ElysianTravels.payload.request;

import com.manjot.ElysianTravels.model.enums.PassengerType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Builder.Default
    PassengerType passengerType = PassengerType.STANDARD;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}