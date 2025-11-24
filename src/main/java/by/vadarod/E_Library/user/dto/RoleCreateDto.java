package by.vadarod.E_Library.user.dto;

import lombok.*;

import java.util.List;


@Data
public class RoleCreateDto {
    private String name;
    private List<UserUppDto> userEntityList;

}
