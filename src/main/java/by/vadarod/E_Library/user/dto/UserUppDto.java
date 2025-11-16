package by.vadarod.E_Library.user.dto;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserUppDto extends UserCreateDto{
    private long id;
}
