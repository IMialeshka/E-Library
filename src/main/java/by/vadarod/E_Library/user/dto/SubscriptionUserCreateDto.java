package by.vadarod.E_Library.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
public class SubscriptionUserCreateDto {
    @NotNull(message = "Не заданно значение для пользователя")
    private UserUppDto user;
    @NotNull(message = "Не заданно значение для подписки")
    private SubscriptionUppDto subscription;
    private Date startDate;
    private Date endDate;
}
