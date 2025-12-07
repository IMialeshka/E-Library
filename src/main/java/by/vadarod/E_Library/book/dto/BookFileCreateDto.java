package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.book.entity.BookEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BookFileCreateDto {
    private String fileName;
    private BookEntity book;
}
