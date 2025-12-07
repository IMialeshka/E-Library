package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target = "book", expression = "java(MappingRulesForBooksDomain.idToBook(reviewCreateDto.getBookId(), bookRepository))")
    @Mapping(target = "user", expression = "java(MappingRulesForBooksDomain.idToUser(reviewCreateDto.getUserId(), userRepository))")
    ReviewEntity reviewDtoToReview(ReviewCreateDto reviewCreateDto, @Context BookRepository bookRepository, @Context UserRepository userRepository);

    @Mapping(target = "book", expression = "java(MappingRulesForBooksDomain.idToBook(reviewUppDto.getBookId(), bookRepository))")
    @Mapping(target = "user", expression = "java(MappingRulesForBooksDomain.idToUser(reviewUppDto.getUserId(), userRepository))")
    ReviewEntity reviewUppDtoToReview(ReviewUppDto reviewUppDto, @Context BookRepository bookRepository, @Context UserRepository userRepository);

    @Mapping(target = "bookId", expression = "java(reviewEntity.getBook().getId())")
    @Mapping(target = "userId", expression = "java(reviewEntity.getUser().getId())")
    ReviewUppDto reviewDtoToReviewUppDto(ReviewEntity reviewEntity);
}
