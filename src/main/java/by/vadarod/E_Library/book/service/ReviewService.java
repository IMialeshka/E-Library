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
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    @Secured({"READER"})
    public void dellById(long id) {
        reviewRepository.deleteById(id);
        ReviewEntity reviewEntity = reviewRepository.getById(id);
        List<ReviewEntity> reviewEntityList = reviewRepository.findByBookId(reviewEntity.getBook().getId());
        long rating = 0;
        for (ReviewEntity review : reviewEntityList) {
            rating += review.getRating();
        }
        double ratingBook = 1.0*rating/reviewEntityList.size();
        reviewEntity.getBook().setRating(ratingBook);
        bookRepository.save(reviewEntity.getBook());
    }

    public List<ReviewUppDto> getBookReviews(long id) {
        return reviewRepository.findByBookId(id).stream().map(reviewMapper::reviewDtoToReviewUppDto).toList();
    }

    @Secured({"READER"})
    public void saveReview(ReviewCreateDto reviewCreateDto) {
        ReviewEntity reviewEntity = reviewMapper.reviewDtoToReview(reviewCreateDto, bookRepository, userRepository);
        reviewRepository.save(reviewEntity);

        List<ReviewEntity> reviewEntityList = reviewRepository.findByBookId(reviewEntity.getBook().getId());
        long rating = 0;
        for (ReviewEntity review : reviewEntityList) {
            rating += review.getRating();
        }
        double ratingBook = 1.0*rating/reviewEntityList.size();
        reviewEntity.getBook().setRating(ratingBook);
        bookRepository.save(reviewEntity.getBook());
    }

    @Secured({"READER"})
    public void saveUppReview(ReviewUppDto reviewUppDto) {
        ReviewEntity reviewEntity = reviewMapper.reviewUppDtoToReview(reviewUppDto, bookRepository, userRepository);
        reviewRepository.save(reviewEntity);

        List<ReviewEntity> reviewEntityList = reviewRepository.findByBookId(reviewEntity.getBook().getId());
        long rating = 0;
        for (ReviewEntity review : reviewEntityList) {
            rating += review.getRating();
        }
        double ratingBook = 1.0*rating/reviewEntityList.size();
        reviewEntity.getBook().setRating(ratingBook);
        bookRepository.save(reviewEntity.getBook());
    }
}
