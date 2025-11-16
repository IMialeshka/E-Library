package by.vadarod.E_Library.user.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionUppDto extends SubscriptionCreateDto{
    private long id;
}
