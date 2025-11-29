package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
@Tag(name = "Контролер для ролей", description = "Работа с ролями")
public class RoleRestController {
    private final RoleService roleService;
    @PostMapping(value = "/create-role", consumes = "application/json")
    public ResponseEntity<RoleCreateDto> addNewRole (@RequestBody RoleCreateDto roleCreateDto){
        roleService.saveRole(roleCreateDto);
        return new ResponseEntity<>(roleCreateDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/upp-role", consumes = "application/json")
    public ResponseEntity<RoleCreateDto> uppRole (@RequestBody RoleUppDto roleUppDto){
        roleService.saveUppRole(roleUppDto);
        return new ResponseEntity<>(roleUppDto, HttpStatus.OK);
    }

    @PostMapping(value = "/dell-role", consumes = "application/json")
    public ResponseEntity dellRole (@RequestBody RoleUppDto roleUppDto){
        roleService.dellById(roleUppDto.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение роли", description = "Вернуть роль по Id")
    public ResponseEntity<RoleUppDto> getRoleById ( @PathVariable long id){
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all-rols", consumes = "application/json")
    public ResponseEntity<List<RoleUppDto>> getAllRoles (){
        List<RoleUppDto> roleUppDtoList = roleService.getAllRoles();
        return new ResponseEntity<List<RoleUppDto>>(roleUppDtoList, HttpStatus.OK);
    }
}
