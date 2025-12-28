package by.vadarod.E_Library.jwt.controller;

import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;
import by.vadarod.E_Library.jwt.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "oauth")
public class RefreshTokenController {

    private  final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/refresh")
    public JwtAuthenticationResponse refresh(@RequestParam("refresh_token")  String token) {
        return refreshTokenService.refresh(token);
    }
}
