package by.vadarod.E_Library.user.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper= true)
public class SubscriptionUserUppDto extends SubscriptionUserCreateDto {
    private long id;
}
