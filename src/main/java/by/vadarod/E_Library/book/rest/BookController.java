package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.service.BookFileService;
import by.vadarod.E_Library.book.service.BookService;
import by.vadarod.E_Library.tools.exception.model.FileLoadingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    private final BookFileService bookFileService;
    private final ResourceLoader resourceLoader;

    @PostMapping(value = "/create-book", consumes = "application/json")
    public BookCreateDto addNewUser (
            @Validated @RequestBody BookCreateDto bookCreateDto){
        bookService.saveBook(bookCreateDto);
        return bookCreateDto;
    }

    @PostMapping(value = "/upload-book")
    public void uploadFile(MultipartFile file, Long id) throws FileLoadingException {
        bookFileService.uploadFileForBook(id, file);
    }



    @GetMapping(value = "/author-books/{id}/{page}/{size}")
    public List<BookUppDto> getBooksByAuthor (@PathVariable long id,   @PathVariable int page, @PathVariable int size){
        List<BookUppDto> bookUppDtoList = bookService.getAllBooksAuthor(id,  page, size);
        return bookUppDtoList;
    }

    @GetMapping(value = "/genre-books/{genre}/{page}/{size}")
    public List<BookUppDto> getBooksByGenre (@PathVariable String genre,  @PathVariable int page, @PathVariable int size){
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.valueOf(genre.toUpperCase()),  page, size);
        return bookUppDtoList;
    }

    @GetMapping(value = "/genres-books/{genres}/{page}/{size}")
    public List<BookUppDto> getBooksByGenres (@PathVariable String genres,   @PathVariable int page, @PathVariable int size){
        List<Genre> genresList = Arrays.stream(genres.split("&")).map(g -> Genre.valueOf(g)).toList();
        List<BookUppDto> bookUppDtoList = bookService.getGenresBooks(genresList, page,  size);
        return bookUppDtoList;
    }


    @GetMapping(value = "/all_books/{page}/{size}")
    public List<BookUppDto> getBooks (@PathVariable int page, @PathVariable int size){
        return bookService.getAllBooks(page, size);
    }

}
