package by.vadarod.E_Library.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Data
@Schema(name = "DTO для новой роли")
public class RoleCreateDto {
    @Schema(description = "Наименование роли", example = "reader 1")
    @NotBlank(message = "Не указано наименование роли")
    private String name;
    private Map<Long, String> userIdList = new HashMap<>();
}
