package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.repository.BookRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", expression = "java(MappingRulesForBooksDomain.idsToBooksList(authorDto.getBooksId(), bookRepository))")
    AuthorEntity authorDtoToAuthor(AuthorCreateDto authorDto, @Context BookRepository bookRepository);

    @Mapping(target = "books", expression = "java(MappingRulesForBooksDomain.idsToBooksList(authorDto.getBooksId(), bookRepository))")
    AuthorEntity authorUppDtoToAuthor(AuthorUppDto authorDto, @Context BookRepository bookRepository);

    @Mapping(target = "booksId", expression = "java(MappingRulesForBooksDomain.booksToIdsList(authorEntity.getBooks()))")
    AuthorUppDto authorDtoToAuthorUppDto(AuthorEntity authorEntity);

}
