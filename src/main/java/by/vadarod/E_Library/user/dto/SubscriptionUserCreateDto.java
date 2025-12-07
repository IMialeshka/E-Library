package by.vadarod.E_Library.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
public class SubscriptionUserCreateDto {
    @NotNull(message = "Не заданно значение для пользователя")
    private Long userId;
    @NotNull(message = "Не заданно значение для подписки")
    private Long subscriptionId;
    private Date startDate;
    private Date endDate;
}
