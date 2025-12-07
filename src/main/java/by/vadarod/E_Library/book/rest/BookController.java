package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "/create-book", consumes = "application/json")
    public BookCreateDto addNewUser (
            @Validated @RequestBody BookCreateDto bookCreateDto){
        bookService.saveBook(bookCreateDto);
        return bookCreateDto;
    }

    @GetMapping(value = "/author-books/{id}")
    public List<BookUppDto> getBooksByAuthor (@PathVariable long id){
        List<BookUppDto> bookUppDtoList = bookService.getAllBooksAuthor(id);
        return bookUppDtoList;
    }

    @GetMapping(value = "/genre-books/{genre}")
    public List<BookUppDto> getBooksByGenre (@PathVariable String genre){
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.valueOf(genre.toUpperCase()));
        return bookUppDtoList;
    }

    @GetMapping(value = "/genres-books/{genres}")
    public List<BookUppDto> getBooksByGenres (@PathVariable String genres){
        List<Genre> genresList = Arrays.stream(genres.split("&")).map(g -> Genre.valueOf(g)).toList();
        List<BookUppDto> bookUppDtoList = bookService.getGenresBooks(genresList);
        return bookUppDtoList;
    }
}
