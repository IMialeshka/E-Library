package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.jwt.model.JwtAuthenticationResponse;
import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/limited/all_users/{page}/{sizePage}")
    public List<UserUppDto> getAllUsers (@PathVariable int page, @PathVariable int sizePage)
    {
        return userService.getAllUsers(page, sizePage);
    }

    @GetMapping(value = "/limited/{id}")
    public UserUppDto getUserById(@PathVariable  long id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/for_all/create_reader", consumes = "application/json")
    public JwtAuthenticationResponse createReader(@Validated @RequestBody UserCreateDto userCreateDto) throws UserLoginException {
        return userService.saveUser(userCreateDto);
    }

    @PostMapping(value = "/limited/create_librarian", consumes = "application/json")
    public UserCreateDto createLibrarian(@Validated @RequestBody UserCreateDto userCreateDto) throws UserLoginException {
        userService.saveLibrarian(userCreateDto);
        return userCreateDto;
    }

    @PostMapping(value = "/limited/upp_user", consumes = "application/json")
    public UserUppDto uppUser(@Validated @RequestBody UserUppDto userUppDto) throws UserLoginException {
        userService.saveUppUser(userUppDto);
        return userUppDto;
    }

    @PostMapping(value = "/limited/dell_user", consumes = "application/json")
    public void dellUserById(@Validated @RequestBody UserUppDto userUppDto) {
        userService.dellById(userUppDto.getId());
    }

    @PostMapping(value = "/limited/add_favorites/{idBook}")
    public void addToFavorites(@PathVariable long idBook, @AuthenticationPrincipal UserDetails userDetails) {
        userService.saveBookToFavorites(userDetails.getUsername(), idBook);
    }

    @PostMapping(value = "/limited/dell_favorites/{idBook}")
    public void dellToFavorites(@PathVariable long idBook, @AuthenticationPrincipal UserDetails userDetails) {
        userService.dellBookFromFavorites(userDetails.getUsername(), idBook);
    }

    @GetMapping(value = "/limited/favorites")
    public List<BookUppDto> getFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getFavoritesOfUser(userDetails.getUsername());
    }

}
