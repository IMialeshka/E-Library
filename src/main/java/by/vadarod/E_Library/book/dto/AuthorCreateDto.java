package by.vadarod.E_Library.book.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCreateDto {
    private String name;
    private List<BookUppDto> books;
}
