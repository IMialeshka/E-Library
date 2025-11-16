package by.vadarod.E_Library.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleUppDto extends RoleCreateDto{
    private long id;
}
