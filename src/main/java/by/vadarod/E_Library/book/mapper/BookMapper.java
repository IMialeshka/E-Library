package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target = "authors", source = "authors", ignore = true)
    BookEntity bookDtoToBook(BookCreateDto bookDto);

    @Mapping(target = "authors", source = "authors", ignore = true)
    BookEntity bookUppDtoToBook(BookUppDto bookDto);

    @Mapping(target = "authors", source = "authors", ignore = true)
    BookUppDto bookToBookUppDto(BookEntity bookEntity);
}
