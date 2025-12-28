package by.vadarod.E_Library.book.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class BookFileCreateDto {
    private String fileName;
    private String previewFileName;
    @Positive
    private long bookId;
}
