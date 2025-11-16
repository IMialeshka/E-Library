package by.vadarod.E_Library.user.dto;

import lombok.*;

import java.util.Date;

@Data
public class SubscriptionUserCreateDto {
    private UserUppDto user;
    private SubscriptionUppDto subscription;
    private Date startDate;
    private Date endDate;
}
