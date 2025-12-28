package by.vadarod.E_Library.book.service;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private final SubscriptionUserRepository subscriptionUserRepository;
    private final UserRepository userRepository;

    public List<BookUppDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return bookRepository.findAll(pageable).stream().map(bookMapper::bookToBookUppDto).toList();

    }

    @Secured({"LIBRARIAN", "ADMIN"})
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

    @Secured({"LIBRARIAN", "ADMIN"})
    public void saveBook(BookCreateDto bookCreateDto) {
        BookEntity bookEntity = bookMapper.bookDtoToBook(bookCreateDto, authorRepository, reviewRepository, bookFileRepository);
        bookRepository.save(bookEntity);
    }

    @Secured({"LIBRARIAN", "ADMIN"})
    public void saveUppBook(BookUppDto bookUppDto) {
        BookEntity bookEntity = bookMapper.bookUppDtoToBook(bookUppDto, authorRepository, reviewRepository, bookFileRepository);
        bookRepository.save(bookEntity);
    }


    public List<BookUppDto> getAllBooksAuthor(long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        List<BookEntity> bookEntitiesList = bookRepository.findByAuthorsId(id, pageable);
        return bookEntitiesList.stream().map(bookMapper::bookToBookUppDto).toList();
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

    public List<BookUppDto> getRatingBooks(double rating, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return bookRepository.findByRatingGreaterThan(rating, pageable).stream().map(bookMapper::bookToBookUppDto).toList();
    }

    public void downloadBook(long idBook, String login) {
        UserEntity user = userRepository.findByLogin(login).orElse(null);
        if (user != null) {
            Date currDate = new Date();
            if (subscriptionUserRepository.findActiveSubscriptionOfUser(user, currDate, currDate).isPresent()) {
                Optional<BookFileEntity> bookFileEntity =  bookFileRepository.findByBookId(idBook);
                if (bookFileEntity.isPresent()) {
                    Path downloadsPath = Paths.get(System.getProperty("user.home"),"Downloads", bookFileEntity.get().getFileName());
                    Path sourcePath = Paths.get(bookFileService.getPathToLibrary()+ File.separator + bookFileEntity.get().getFileName());
                    try {
                        Files.copy(sourcePath, downloadsPath,  StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                } else {
                    throw new RuntimeException("Нет файла для скачивания.");
                }

            } else {
                throw new RuntimeException("У пользователя нет актуальной подписки.");
            }
        } else {
            throw new RuntimeException("Не тот пользователь.");
        }

    }



}
