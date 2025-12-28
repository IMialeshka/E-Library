package by.vadarod.E_Library.book.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ReviewCreateDto {
    private long userId;
    @PositiveOrZero(message = "Райтинг должен быть >= 0")
    private short rating;
    private String text;
    @NotNull(message = "Укажите книгу")
    private long bookId;
}
