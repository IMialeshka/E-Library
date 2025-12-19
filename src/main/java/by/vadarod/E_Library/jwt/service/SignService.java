package by.vadarod.E_Library.jwt.service;

import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;
import by.vadarod.E_Library.jwt.model.UserAuthenticationRequest;
import by.vadarod.E_Library.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService {
private final UserService userService;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
private final UserDetailsService userDetailsService;
private final RefreshTokenService refreshTokenService;

public JwtAuthenticationResponse signUp(UserAuthenticationRequest userAuthenticationRequest) {
    UserDetails userDetails = userService.getUserDetails(userAuthenticationRequest.getLogin());
    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setAccessToken(jwtService.generateToken(userDetails));
    return jwtAuthenticationResponse;
}

public JwtAuthenticationResponse signIn(UserAuthenticationRequest userAuthenticationRequest) {
    UserDetails user = userDetailsService.loadUserByUsername(userAuthenticationRequest.getLogin());
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthenticationRequest.getLogin(), userAuthenticationRequest.getPassword(), user.getAuthorities());
    authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    SecurityContextHolder.setContext(securityContext);
    String jwt = jwtService.generateToken(user);

    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setAccessToken(jwt);
    jwtAuthenticationResponse.setRefreshToken(refreshTokenService.generateRefreshToken(userAuthenticationRequest.getLogin()));
    return jwtAuthenticationResponse;
}
}
