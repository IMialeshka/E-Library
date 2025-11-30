package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.user.dto.UserUppDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReviewCreateDto {

    @NotNull
    private UserUppDto user;
    @Positive
    private short rating;
    private String text;
    @NotNull
    private BookUppDto book;
}
