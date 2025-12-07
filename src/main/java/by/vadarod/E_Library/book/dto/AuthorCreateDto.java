package by.vadarod.E_Library.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCreateDto {
    @NotBlank
    private String name;
    private List<Long> booksId;
}
