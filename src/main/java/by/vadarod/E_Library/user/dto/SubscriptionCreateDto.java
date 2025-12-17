package by.vadarod.E_Library.user.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Data
public class SubscriptionCreateDto {
    @NotBlank(message = "Не указано наименование подписки")
    private String name;
    @Positive(message = "Стотмость должна быть больше нуля")
    private double price;
    private short lengthMonth;
    private int lengthDay;

    @AssertFalse(message = "Длительность должна быть больше или равна нулю нулю")
    public boolean isValidLength() {
        return lengthMonth <= 0 && lengthDay <= 0;
    }

    @AssertFalse(message = "Укажите длительность ИЛИ в днях ИЛИ в месяцах")
    public boolean isValidLengths() {
        return lengthMonth >= 1 && lengthDay >= 1;
    }
}
