package by.vadarod.E_Library.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;


@Data
@Schema(name = "DTO для новой роли")
public class RoleCreateDto {
    @Schema(description = "Наименование роли", example = "reader 1")
    private String name;
    private List<UserUppDto> userEntityList;

}
