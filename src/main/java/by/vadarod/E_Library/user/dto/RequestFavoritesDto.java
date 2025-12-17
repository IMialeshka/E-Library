package by.vadarod.E_Library.user.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestFavoritesDto {
    @Positive(message = "Укажите пользователя")
    private Long userId;
    @Positive(message = "Укажите книгу")
    private Long bookId;
}
