package by.vadarod.E_Library.book.service;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final BookFileService bookFileService;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;
    private final BookFileRepository bookFileRepository;

    public List<BookUppDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return bookRepository.findAll(pageable).stream().map(bookMapper::bookToBookUppDto).toList();

    }

    @Secured({"LIBRARIAN"})
    public void dellById(long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);

        if (bookEntity != null) {
            bookEntity.getBookFiles().forEach(bookFile -> bookFileService.dellFile(bookFile.getFileName()));
            bookRepository.deleteById(id);
        }
    }

    public BookUppDto getById(long id) {
        return bookMapper.bookToBookUppDto(bookRepository.getById(id));
    }

    @Secured({"LIBRARIAN"})
    public void saveBook(BookCreateDto bookCreateDto) {
        BookEntity bookEntity = bookMapper.bookDtoToBook(bookCreateDto, authorRepository, reviewRepository, bookFileRepository);
        bookRepository.save(bookEntity);
    }

    @Secured({"LIBRARIAN"})
    public void saveUppBook(BookUppDto bookUppDto) {
        BookEntity bookEntity = bookMapper.bookUppDtoToBook(bookUppDto, authorRepository, reviewRepository, bookFileRepository);
        bookRepository.save(bookEntity);
    }


    public List<BookUppDto> getAllBooksAuthor(long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return bookRepository.findByAuthorsId(id, pageable).stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public List<BookUppDto> getGenreBooks(Genre genre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        List<BookEntity> bookEntitiesList = bookRepository.findByGenre(genre, pageable);
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public List<BookUppDto> getGenresBooks(List<Genre> genres, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return bookRepository.findByGenreIn(genres, pageable).stream().map(bookMapper::bookToBookUppDto).toList();
    }



}
