package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.BookFileCreateDto;
import by.vadarod.E_Library.book.dto.BookFileUppDto;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.repository.BookRepository;
import org.mapstruct.Context;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookFileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", expression = "java(MappingRulesForBooksDomain.idToBook(bookFileCreateDto.getBookId(), bookRepository))")
    BookFileEntity bookFileCreateDtoToBookFileEntity(BookFileCreateDto bookFileCreateDto, @Context BookRepository bookRepository);

    @Mapping(target = "book", expression = "java(MappingRulesForBooksDomain.idToBook(bookFileUppDto.getBookId(), bookRepository))")
    BookFileEntity bookFileUppDtoToBookFileEntity(BookFileUppDto bookFileUppDto, @Context BookRepository bookRepository);

    @Mapping(target = "bookId", expression = "java(bookFileEntity.getBook().getId())")
    BookFileUppDto bookFileDtoToBookFileEntity(BookFileEntity bookFileEntity);
}
