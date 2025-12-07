package by.vadarod.E_Library.user.dto;

import by.vadarod.E_Library.book.dto.BookUppDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
public class UserCreateDto {
    @NotBlank(message = "Не заданно имя")
    private String name;
    @NotBlank(message = "Не заданно имя прользователя")
    private String login;
    @NotBlank(message = "Не задан пароль")
    private String password;
    @NotNull(message = "Не задана роль")
    private RoleUppDto role;
    private List<BookUppDto> favorites;
}
