package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.book.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateDto {
    @NotBlank(message = "Укажите название книги")
    private String name;
    private Map<Long, String> authorsId = new HashMap<>();
    @NotNull(message = "Укажите жанр")
    private Genre genre;
    private List<Long> bookFilesId = new ArrayList<>();
    private Map<Long, String> usersId = new HashMap<>();
    private List<ReviewCreateDto> reviewEntityListId = new ArrayList<>();
    private double rating;


}
