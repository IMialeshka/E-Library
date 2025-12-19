package by.vadarod.E_Library.jwt.controller;

import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;
import by.vadarod.E_Library.jwt.model.UserAuthenticationRequest;
import by.vadarod.E_Library.jwt.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "outh")
@RequiredArgsConstructor
public class JwtController {

    private final SignService signService;

    @PostMapping(value = "sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody UserAuthenticationRequest userAuthenticationRequest) {
        return signService.signIn(userAuthenticationRequest);
    }
}
