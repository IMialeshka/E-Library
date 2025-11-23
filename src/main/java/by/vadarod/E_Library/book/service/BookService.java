package by.vadarod.E_Library.book.service;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final BookFileService bookFileService;
    private final AuthorRepository authorRepository;


    public List<BookUppDto> getAllBooks() {
        List<BookEntity> bookEntitiesList = bookRepository.findAll();
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();

    }

    public void dellById(long id) {
        getById(id).getBookFiles().forEach(bookFileUppDto -> bookFileService.dellFile(bookFileUppDto.getFileName()));
        bookRepository.deleteById(id);
    }

    public BookUppDto getById(long id) {
        return bookMapper.bookToBookUppDto(bookRepository.getById(id));
    }

    public void saveBook(BookCreateDto bookCreateDto) {
        BookEntity bookEntity = bookMapper.bookDtoToBook(bookCreateDto);
        bookRepository.save(bookEntity);
    }

    public void saveUppBook(BookUppDto bookUppDto) {
        BookEntity bookEntity = bookMapper.bookUppDtoToBook(bookUppDto);
        bookRepository.save(bookEntity);
    }


    public List<BookUppDto> getAllBooksAuthor(long id) {
        List<BookEntity> bookEntitiesList = authorRepository.getReferenceById(id).getBooks();
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public List<BookUppDto> getGenreBooks(Genre genre) {
        List<BookEntity> bookEntitiesList = bookRepository.findByGenre(genre);
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public List<BookUppDto> getGenresBooks(List<Genre> genres) {
        List<BookEntity> bookEntitiesList = bookRepository.findByGenreIn(genres);
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();
    }



}
