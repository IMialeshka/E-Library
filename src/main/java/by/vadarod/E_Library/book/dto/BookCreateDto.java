package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.user.dto.UserUppDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateDto {
    private String name;
    private List<AuthorUppDto> authors;
    private Genre genre;
    private String pathFile;
    private List<UserUppDto> users;
    private List<ReviewUppDto> reviewEntityList;
}
