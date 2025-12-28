package by.vadarod.E_Library.book.service;


import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.mapper.ReviewMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.user.entity.UserEntity;
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
    public void dellById(long id, String login) {
        ReviewEntity reviewEntity = reviewRepository.getById(id);        UserEntity user = userRepository.findByLogin(login).get();
        if (!user.equals(reviewEntity.getUser())) {
            throw new RuntimeException("Удалить можно только свой отзыв");
        }
        reviewRepository.deleteById(id);
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
    public ReviewUppDto saveReview(ReviewCreateDto reviewCreateDto, String login) {
        ReviewEntity reviewEntity = reviewMapper.reviewDtoToReview(reviewCreateDto, bookRepository);
        UserEntity user = userRepository.findByLogin(login).get();
        reviewEntity.setUser(user);
        ReviewEntity reviewNew = reviewRepository.save(reviewEntity);

        List<ReviewEntity> reviewEntityList = reviewRepository.findByBookId(reviewNew.getBook().getId());
        long rating = 0;
        for (ReviewEntity review : reviewEntityList) {
            rating += review.getRating();
        }
        double ratingBook = 1.0*rating/reviewEntityList.size();
        reviewNew.getBook().setRating(ratingBook);
        bookRepository.save(reviewNew.getBook());
        long i = reviewNew.getBook().getId();
        return reviewMapper.reviewDtoToReviewUppDto(reviewNew);
    }

    @Secured({"READER"})
    public void saveUppReview(ReviewUppDto reviewUppDto, String login) {
        ReviewEntity reviewEntity = reviewMapper.reviewUppDtoToReview(reviewUppDto, bookRepository, userRepository);
        UserEntity user = userRepository.findByLogin(login).get();
        if (!user.equals(reviewEntity.getUser())) {
            throw new RuntimeException("Редактировать можно только свой отзыв");
        }
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
