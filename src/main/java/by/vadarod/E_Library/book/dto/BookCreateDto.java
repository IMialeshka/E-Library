package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.book.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateDto {
    @NotBlank
    private String name;
    private List<Long> authorsId;
    @NotNull
    private Genre genre;
    private List<Long> bookFilesId;
    private List<Long> usersId;
    private List<Long> reviewEntityListId;
    @PositiveOrZero
    private double rating;


}
