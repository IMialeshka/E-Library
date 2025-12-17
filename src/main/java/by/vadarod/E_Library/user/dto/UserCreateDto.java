package by.vadarod.E_Library.user.dto;

import by.vadarod.E_Library.book.dto.BookUppDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserCreateDto {
    @NotBlank(message = "Не заданно имя")
    private String name;
    @NotBlank(message = "Не заданно имя прользователя")
    private String login;
    @NotBlank(message = "Не задан пароль")
    private String password;
    @Positive(message = "Не задана роль")
    private Long roleId;
    private Map<Long, String> favoritesIdList = new HashMap<>();
}
