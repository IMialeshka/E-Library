package by.vadarod.E_Library.book.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class BookFileCreateDto {
    private String fileName;
    @Positive
    private long bookId;
}
