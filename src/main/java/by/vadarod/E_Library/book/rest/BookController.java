package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.service.BookService;
import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "/create-book", consumes = "application/json")
    public ResponseEntity<BookCreateDto> addNewUser (@RequestBody BookCreateDto bookCreateDto){
        bookService.saveBook(bookCreateDto);
        return new ResponseEntity<>(bookCreateDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/author-books/{id}")
    public ResponseEntity<List<BookUppDto>> getBooksByAuthor (@PathVariable long id){
        List<BookUppDto> bookUppDtoList = bookService.getAllBooksAuthor(id);
        return new ResponseEntity<>(bookUppDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/genre-books/{genre}")
    public ResponseEntity<List<BookUppDto>> getBooksByGenre (@PathVariable String genre){
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.valueOf(genre.toUpperCase()));
        return new ResponseEntity<>(bookUppDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/genres-books/{genres}")
    public ResponseEntity<List<BookUppDto>> getBooksByGenres (@PathVariable String genres){
        List<Genre> genresList = Arrays.stream(genres.split("&")).map(g -> Genre.valueOf(g)).toList();
        List<BookUppDto> bookUppDtoList = bookService.getGenresBooks(genresList);
        return new ResponseEntity<>(bookUppDtoList, HttpStatus.OK);
    }
}
