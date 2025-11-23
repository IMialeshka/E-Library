package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.BookFileCreateDto;
import by.vadarod.E_Library.book.dto.BookFileUppDto;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookFileMapper {
    @Mapping(target = "id", ignore = true)
    BookFileEntity bookFileCreateDtoToBookFileEntity(BookFileCreateDto bookFileCreateDto);

    BookFileEntity bookFileUppDtoToBookFileEntity(BookFileUppDto bookFileUppDto);

    BookFileUppDto bookFileDtoToBookFileEntity(BookFileEntity bookFileEntity);
}
