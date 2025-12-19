package by.vadarod.E_Library.jwt.model;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;

    private String refreshToken;
}
