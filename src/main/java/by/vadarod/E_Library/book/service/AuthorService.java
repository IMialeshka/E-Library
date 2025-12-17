package by.vadarod.E_Library.book.service;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.mapper.AuthorMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<AuthorUppDto> getAllAuthor() {
        List<AuthorEntity> authorUppDtoList = authorRepository.findAll();
        return authorUppDtoList.stream().map(authorMapper::authorDtoToAuthorUppDto).toList();

    }

    public void dellById(long id) {
        authorRepository.deleteById(id);
    }

    public AuthorUppDto getById(long id) {
        return authorMapper.authorDtoToAuthorUppDto(authorRepository.getById(id));
    }

    public void saveAuthor(AuthorCreateDto authorCreateDto) {
        AuthorEntity authorEntity = authorMapper.authorDtoToAuthor(authorCreateDto, bookRepository);
        authorRepository.save(authorEntity);
    }

    public void saveUppAuthor(AuthorUppDto authorUppDto) {
        AuthorEntity authorEntity = authorMapper.authorUppDtoToAuthor(authorUppDto, bookRepository);
        authorRepository.save(authorEntity);
    }
}
