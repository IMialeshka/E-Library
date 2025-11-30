package by.vadarod.E_Library.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Data
@Schema(name = "DTO для новой роли")
public class RoleCreateDto {
    @Schema(description = "Наименование роли", example = "reader 1")
    @NotBlank(message = "Не указано наименование роли")
    private String name;
    private List<UserUppDto> userEntityList;

}
