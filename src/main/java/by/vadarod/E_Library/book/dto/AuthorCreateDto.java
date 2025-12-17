package by.vadarod.E_Library.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCreateDto {
    @NotBlank
    private String name;
    private Map<Long, String> booksId = new HashMap<>();
}
