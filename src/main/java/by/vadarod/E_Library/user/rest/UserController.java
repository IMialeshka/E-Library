package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/all_users")
    public List<UserUppDto> getAllUsers ()
    {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserUppDto getUserById(@PathVariable  long id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/create_user", consumes = "application/json")
    public UserCreateDto createUser(@Validated @RequestBody UserCreateDto userCreateDto) throws UserLoginException {
        userService.saveUser(userCreateDto);
        return userCreateDto;
    }

    @PostMapping(value = "/upp_user", consumes = "application/json")
    public UserUppDto uppUser(@Validated @RequestBody UserUppDto userUppDto) throws UserLoginException {
        userService.saveUppUser(userUppDto);
        return userUppDto;
    }

    @PostMapping(value = "/dell_user", consumes = "application/json")
    public void dellUserById(@PathVariable UserUppDto userUppDto) {
        userService.dellById(userUppDto.getId());
    }
}
