package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.user.dto.UserUppDto;
import lombok.Data;

@Data
public class ReviewCreateDto {

    private UserUppDto user;
    private short rating;
    private String text;
    private BookUppDto book;
}
