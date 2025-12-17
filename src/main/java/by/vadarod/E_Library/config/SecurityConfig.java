package by.vadarod.E_Library.config;

import by.vadarod.E_Library.tools.security.CustomAuthenticationPoint;
import by.vadarod.E_Library.tools.security.CustomerAccessDenied;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder byCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests.requestMatchers("users/**", "roles/**").permitAll()
                                .requestMatchers("books/**").authenticated())
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new CustomAuthenticationPoint());
                    ex.accessDeniedHandler(new CustomerAccessDenied());
                });
        return http.build();
    }
}
