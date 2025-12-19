package by.vadarod.E_Library.jwt.service;

import by.vadarod.E_Library.jwt.entity.RefreshToken;
import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;

import by.vadarod.E_Library.jwt.repository.RefreshTokenRepository;
import by.vadarod.E_Library.tools.exception.model.RefreshTokenException;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-token.lifeTime}")
    private long refreshTokenTime;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository  userRepository;

    private final JwtService jwtService;

    public JwtAuthenticationResponse refresh(String token) {
        Optional<RefreshToken> byToken = refreshTokenRepository.findByToken(token);

        if (byToken.isEmpty()) {
            throw  new RefreshTokenException("Токен не корректен");
        }
            RefreshToken userToken = byToken.get();
            if (userToken.getDateTimeToken().isBefore(LocalDateTime.now())) {
                throw new RefreshTokenException("Токен истек");
            }
        UserEntity user = userToken.getUser();

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwtService.generateToken(user));
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setDateTimeToken(LocalDateTime.now().plusMinutes(refreshTokenTime));
        jwtAuthenticationResponse.setRefreshToken(userToken.getToken());
        refreshTokenRepository.save(userToken);
        return jwtAuthenticationResponse;
    }

    public String generateRefreshToken(String userName) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserLogin(userName);

        RefreshToken refreshToken;
        if (optionalRefreshToken.isEmpty()) {
            refreshToken = new RefreshToken();
            UserEntity user = userRepository.findByLogin(userName).orElseThrow(() -> new RefreshTokenException("Пользователя не нашли"));
            refreshToken.setUser(user);
        } else {
            refreshToken = optionalRefreshToken.get();
        }

        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setDateTimeToken(LocalDateTime.now().plusMinutes(refreshTokenTime));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }
}
