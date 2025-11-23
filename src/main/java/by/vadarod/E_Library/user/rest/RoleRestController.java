package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RoleRestController {
    private final RoleService roleService;
    @PostMapping(value = "/create-role", consumes = "application/json")
    public ResponseEntity<RoleCreateDto> addNewUser (@RequestBody RoleCreateDto roleCreateDto){
        roleService.saveRole(roleCreateDto);
        return new ResponseEntity<>(roleCreateDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleUppDto> getRoleById (@PathVariable long id){
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }
}
