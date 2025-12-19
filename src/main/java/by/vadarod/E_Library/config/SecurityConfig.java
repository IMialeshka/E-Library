package by.vadarod.E_Library.config;

import by.vadarod.E_Library.jwt.JwtAuthenticationFilter;
import by.vadarod.E_Library.tools.security.CustomAuthenticationPoint;
import by.vadarod.E_Library.tools.security.CustomerAccessDenied;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter  jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder byCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests.requestMatchers("users/**", "roles/**","outh/**", "review/read/**", "oauth/**").permitAll()
                                .requestMatchers("books/**", "review/changes/**").authenticated())
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new CustomAuthenticationPoint());
                    ex.accessDeniedHandler(new CustomerAccessDenied());
                });
        return http.build();
    }
}
