package by.vadarod.E_Library.user.dto;

import by.vadarod.E_Library.book.dto.BookUppDto;
import lombok.*;

import java.util.List;

@Data
public class UserCreateDto {
    private String name;
    private String login;
    private String password;
    private RoleUppDto role;
    private List<BookUppDto> favorites;
}
