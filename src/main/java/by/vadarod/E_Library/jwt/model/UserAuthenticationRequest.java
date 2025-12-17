package by.vadarod.E_Library.jwt.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthenticationRequest {
    @NotBlank(message = "Не заданно имя прользователя")
    private String login;
    @NotBlank(message = "Не задан пароль")
    private String password;
}
