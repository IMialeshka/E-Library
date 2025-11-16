package by.vadarod.E_Library.book.mapper;

import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target ="id", ignore = true)
    ReviewEntity reviewDtoToReview(ReviewCreateDto reviewCreateDto);

    ReviewEntity reviewUppDtoToReview(ReviewUppDto reviewUppDto);

    ReviewUppDto reviewDtoToReviewUppDto(ReviewEntity reviewEntity);
}
