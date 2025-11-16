package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", source = "books", ignore = true)
    AuthorEntity authorDtoToAuthor(AuthorCreateDto authorDto);

    @Mapping(target = "books", source = "books", ignore = true)
    AuthorEntity authorUppDtoToAuthor(AuthorUppDto authorDto);

    @Mapping(target = "books", source = "books", ignore = true)
    AuthorUppDto authorDtoToAuthorUppDto(AuthorEntity authorEntity);
}
