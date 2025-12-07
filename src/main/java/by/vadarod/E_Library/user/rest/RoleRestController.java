package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.tools.exception.model.RoleUseWithUsersException;
import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
@Tag(name = "Контролер для ролей", description = "Работа с ролями")

public class RoleRestController {
    private final RoleService roleService;
    @PostMapping(value = "/create-role", consumes = "application/json")
    public RoleCreateDto addNewRole (@Validated @RequestBody RoleCreateDto roleCreateDto){
        roleService.saveRole(roleCreateDto);
        return roleCreateDto;
    }

    @PostMapping(value = "/upp-role", consumes = "application/json")
    public RoleCreateDto uppRole (@Validated @RequestBody RoleUppDto roleUppDto){
        roleService.saveUppRole(roleUppDto);
        return roleUppDto;
    }

    @PostMapping(value = "/dell-role", consumes = "application/json")
    public void dellRole (@RequestBody RoleUppDto roleUppDto) throws RoleUseWithUsersException {
        roleService.dellById(roleUppDto.getId());;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение роли", description = "Вернуть роль по Id")
    public RoleUppDto getRoleById ( @PathVariable long id){
        return roleService.getById(id);
    }

    @GetMapping(value = "/all-rols")
    public List<RoleUppDto> getAllRoles (){
        List<RoleUppDto> roleUppDtoList = roleService.getAllRoles();
        return roleUppDtoList;
    }
}
