package by.vadarod.E_Library.user.dto;

import lombok.*;


@Data
public class SubscriptionCreateDto {
    private String name;
    private double price;
    private short lengthMonth;
    private int lengthDay;
}
