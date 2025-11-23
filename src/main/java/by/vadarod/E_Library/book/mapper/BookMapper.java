package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target ="id", ignore = true)
    BookEntity bookDtoToBook(BookCreateDto bookDto);

    BookEntity bookUppDtoToBook(BookUppDto bookDto);

    BookUppDto bookToBookUppDto(BookEntity bookEntity);

}
