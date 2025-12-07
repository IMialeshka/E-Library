package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping(value = "/create-author", consumes = "application/json")
    public AuthorCreateDto addAuthor (
            @Validated @RequestBody AuthorCreateDto authorCreateDto){
        authorService.saveAuthor(authorCreateDto);
        return authorCreateDto;
    }

    @GetMapping(value = "/author-all")
    public List<AuthorUppDto> getBooksByAuthor (){
        return authorService.getAllAuthor();
    }
}
