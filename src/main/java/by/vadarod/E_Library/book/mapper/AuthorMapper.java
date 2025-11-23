package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.AuthorCreateDto;
import by.vadarod.E_Library.book.dto.AuthorUppDto;
import by.vadarod.E_Library.book.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    AuthorEntity authorDtoToAuthor(AuthorCreateDto authorDto);


    AuthorEntity authorUppDtoToAuthor(AuthorUppDto authorDto);

    AuthorUppDto authorDtoToAuthorUppDto(AuthorEntity authorEntity);

}
