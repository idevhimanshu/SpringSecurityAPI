package com.example.SecureAuthAPI.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String status;
    private String message;
    private AuthData data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthData {
        private String email;
        private String token;
        private Date expiresIn;
        private long mobileNumber;
    }
}
