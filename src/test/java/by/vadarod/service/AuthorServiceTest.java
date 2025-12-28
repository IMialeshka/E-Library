package by.vadarod.service;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.mapper.AuthorMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.rest.AuthorController;
import by.vadarod.E_Library.book.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorMapper authorMapper;

    @InjectMocks
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    @Test
    public void test_add_newAuthor(){
        AuthorCreateDto authorCreateDto = new AuthorCreateDto();
        authorCreateDto.setName("Test Author");
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("Test Author");
        when(authorMapper.authorDtoToAuthor(any(AuthorCreateDto.class), any())).thenReturn(authorEntity);
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(authorEntity);

        when(authorMapper.authorDtoToAuthorUppDto(any(AuthorEntity.class))).thenAnswer(entity -> {
            AuthorEntity entityIn = entity.getArgument(0);
            AuthorUppDto dto = new AuthorUppDto();
            dto.setId(100);
            dto.setName(entityIn.getName());
            return dto;
        });
        AuthorUppDto authorUppDtoResult = authorService.saveAuthor(authorCreateDto);
        assertEquals("Test Author", authorUppDtoResult.getName());
    }
}
