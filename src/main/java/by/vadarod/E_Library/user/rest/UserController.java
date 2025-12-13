package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.RequestFavoritesDto;
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

    @GetMapping(value = "/all_users/{page}/{sizePage}")
    public List<UserUppDto> getAllUsers (@PathVariable int page, @PathVariable int sizePage)
    {
        return userService.getAllUsers(page, sizePage);
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
    public void dellUserById(@Validated @RequestBody UserUppDto userUppDto) {
        userService.dellById(userUppDto.getId());
    }

    @PostMapping(value = "/add_favorites", consumes = "application/json")
    public void addToFavorites(@Validated @RequestBody RequestFavoritesDto requestFavoritesDto) {
        userService.saveBookToFavorites(requestFavoritesDto.getUserId(), requestFavoritesDto.getBookId());
    }

    @PostMapping(value = "/dell_favorites", consumes = "application/json")
    public void dellToFavorites(@Validated @RequestBody RequestFavoritesDto requestFavoritesDto) {
        userService.dellBookFromFavorites(requestFavoritesDto.getUserId(), requestFavoritesDto.getBookId());
    }

    @GetMapping(value = "/favorites/{id}")
    public List<BookUppDto> getFavorites(@PathVariable  long id) {
        return userService.getFavoritesOfUser(id);
    }

}
