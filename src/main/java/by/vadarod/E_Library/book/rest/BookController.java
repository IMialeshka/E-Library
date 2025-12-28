package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.service.BookFileService;
import by.vadarod.E_Library.book.service.BookService;
import by.vadarod.E_Library.tools.exception.model.FileLoadingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    private final BookFileService bookFileService;
    private final ResourceLoader resourceLoader;


    @PostMapping(value = "/limited/create-book", consumes = "application/json")
    public BookCreateDto addNewUser (
            @Validated @RequestBody BookCreateDto bookCreateDto){
        bookService.saveBook(bookCreateDto);
        return bookCreateDto;
    }

    @PostMapping(value = "/limited/upload-book")
    public void uploadFile(MultipartFile file, Long id) throws FileLoadingException {
        bookFileService.uploadFileForBook(id, file);
    }



    @GetMapping(value = "/for_all/author-books/{id}/{page}/{size}")
    public List<BookUppDto> getBooksByAuthor (@PathVariable long id,   @PathVariable int page, @PathVariable int size){
        List<BookUppDto> bookUppDtoList = bookService.getAllBooksAuthor(id,  page, size);
        return bookUppDtoList;
    }

    @GetMapping(value = "/for_all/genre-books/{genre}/{page}/{size}")
    public List<BookUppDto> getBooksByGenre (@PathVariable String genre,  @PathVariable int page, @PathVariable int size){
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.valueOf(genre.toUpperCase()),  page, size);
        return bookUppDtoList;
    }

    @GetMapping(value = "/for_all/genres-books/{genres}/{page}/{size}")
    public List<BookUppDto> getBooksByGenres (@PathVariable String genres,   @PathVariable int page, @PathVariable int size){
        List<Genre> genresList = Arrays.stream(genres.split("&")).map(g -> Genre.valueOf(g)).toList();
        List<BookUppDto> bookUppDtoList = bookService.getGenresBooks(genresList, page,  size);
        return bookUppDtoList;
    }


    @GetMapping(value = "/for_all/all_books/{page}/{size}")
    public List<BookUppDto> getBooks (@PathVariable int page, @PathVariable int size){
        return bookService.getAllBooks(page, size);
    }

    @GetMapping(value = "/for_all/rating/{rating}/{page}/{size}")
    public List<BookUppDto> getBooksRating (@PathVariable double rating, @PathVariable int page, @PathVariable int size){
        return bookService.getRatingBooks(rating, page, size);
    }

    @GetMapping(value = "/for_all/read_book/{id}", produces = "image/jpeg")
    public Resource getImageAsResource(@PathVariable long id) throws URISyntaxException, IOException {
        return resourceLoader.getResource("file:" + bookFileService.getFileNameForRead(id));
    }

    @Secured({"LIBRARIAN", "ADMIN", "READER"})
    @PostMapping(value = "/limited/download_book")
    public void downloadBook(@RequestParam long idBook, @AuthenticationPrincipal UserDetails userDetails) {
        bookService.downloadBook(idBook, userDetails.getUsername());
    }


}
