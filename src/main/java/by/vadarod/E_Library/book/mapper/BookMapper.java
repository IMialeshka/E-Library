package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target = "authors", expression = "java(MappingRulesForBooksDomain.idsToAuthorsList(bookDto.getAuthorsId(), authorRepository))")
    @Mapping(target = "bookFiles", expression = "java(MappingRulesForBooksDomain.idsToFilesList(bookDto.getBookFilesId(), bookFileRepository))")
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "reviewEntityList", expression = "java(MappingRulesForBooksDomain.idsToReviewList(bookDto.getReviewEntityListId(), reviewRepository))")
    BookEntity bookDtoToBook(BookCreateDto bookDto, @Context AuthorRepository authorRepository, @Context ReviewRepository  reviewRepository, @Context BookFileRepository bookFileRepository);

    @Mapping(target = "authors", expression = "java(MappingRulesForBooksDomain.idsToAuthorsList(bookDto.getAuthorsId(), authorRepository))")
    @Mapping(target = "bookFiles", expression = "java(MappingRulesForBooksDomain.idsToFilesList(bookDto.getBookFilesId(), bookFileRepository))")
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "reviewEntityList", expression = "java(MappingRulesForBooksDomain.idsToReviewList(bookDto.getReviewEntityListId(), reviewRepository))")
    BookEntity bookUppDtoToBook(BookUppDto bookDto, @Context AuthorRepository authorRepository, @Context ReviewRepository  reviewRepository, @Context BookFileRepository bookFileRepository);

    @Mapping(target = "authorsId", expression = "java(MappingRulesForBooksDomain.authorsToIdList(bookEntity.getAuthors()))")
    @Mapping(target = "bookFilesId", expression = "java(MappingRulesForBooksDomain.bookFilesToIdList(bookEntity.getBookFiles()))")
    @Mapping(target = "usersId", ignore = true)
    @Mapping(target = "reviewEntityListId", expression = "java(MappingRulesForBooksDomain.reviewsToIdList(bookEntity.getReviewEntityList()))")
    BookUppDto bookToBookUppDto(BookEntity bookEntity);

}
