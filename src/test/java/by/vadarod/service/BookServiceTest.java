package by.vadarod.service;

import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Mock
    BookMapper bookMapper;

    @Test
    public void test_get_genre_books() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        authorEntity.setId(1);
        List<AuthorEntity> authorEntities = new ArrayList<>();
        authorEntities.add(authorEntity);

        List<BookEntity> bookEntityList = new ArrayList<>();
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthors(authorEntities);
        bookEntityList.add(bookEntity1);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setAuthors(authorEntities);
        bookEntityList.add(bookEntity2);

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setAuthors(authorEntities);
        bookEntityList.add(bookEntity3);

        when(bookRepository.findByGenre(any(), any())).thenReturn(bookEntityList);
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.HORROR, 0, 100);
        assertEquals(3, bookUppDtoList.size());

    }

    @Test
    public void test_get_genre_books_mock() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        authorEntity.setId(1);
        List<AuthorEntity> authorEntities = new ArrayList<>();
        authorEntities.add(authorEntity);

        List<BookEntity> bookEntityList = new ArrayList<>();
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthors(authorEntities);
        bookEntityList.add(bookEntity1);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setAuthors(authorEntities);
        bookEntityList.add(bookEntity2);

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setAuthors(authorEntities);
        bookEntityList.add(bookEntity3);

        when(bookRepository.findByGenre(any(), any())).thenReturn(bookEntityList);
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.HORROR, 0, 100);
        verify(bookMapper, times(3)).bookToBookUppDto(any());
    }

    @Test
    public void test_get_genre_correct_books() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("test");
        authorEntity.setId(1);
        List<AuthorEntity> authorEntities = new ArrayList<>();
        authorEntities.add(authorEntity);

        List<BookEntity> bookEntityList = new ArrayList<>();
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthors(authorEntities);
        bookEntity1.setGenre(Genre.HORROR);
        bookEntityList.add(bookEntity1);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setAuthors(authorEntities);
        bookEntity2.setGenre(Genre.HORROR);
        bookEntityList.add(bookEntity2);

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setAuthors(authorEntities);
        bookEntity3.setGenre(Genre.HORROR);
        bookEntityList.add(bookEntity3);

        when(bookRepository.findByGenre(any(), any())).thenReturn(bookEntityList);
        when(bookMapper.bookToBookUppDto(any())).thenAnswer(entity -> {
            BookEntity bookEntity = entity.getArgument(0);
            BookUppDto bookUppDto = new BookUppDto();
            bookUppDto.setGenre(bookEntity.getGenre());
            return bookUppDto;
        });
        List<BookUppDto> bookUppDtoList = bookService.getGenreBooks(Genre.HORROR, 0, 100);
        assertTrue(bookUppDtoList.stream().allMatch(s->s.getGenre().equals(Genre.HORROR)));
    }
}
