package by.vadarod.E_Library.book.service;


import by.vadarod.E_Library.book.dto.BookCreateDto;
import by.vadarod.E_Library.book.dto.BookUppDto;
import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.mapper.ReviewMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;


    public void dellById(long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewUppDto getById(long id) {
        return reviewMapper.reviewDtoToReviewUppDto(reviewRepository.getById(id));
    }

    public void saveReview(ReviewCreateDto reviewCreateDto) {
        ReviewEntity reviewEntity = reviewMapper.reviewDtoToReview(reviewCreateDto);
        reviewRepository.save(reviewEntity);
    }

    public void saveUppReview(ReviewUppDto reviewUppDto) {
        ReviewEntity reviewEntity = reviewMapper.reviewUppDtoToReview(reviewUppDto);
        reviewRepository.save(reviewEntity);
    }
}
