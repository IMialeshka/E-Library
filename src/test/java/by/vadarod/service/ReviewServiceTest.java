package by.vadarod.service;

import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.mapper.ReviewMapper;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.book.service.ReviewService;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.UserMapper;
import by.vadarod.E_Library.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookMapper bookMapper;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void test_addReview() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setRating((short) 8);
        reviewEntity.setText("test");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(100);
        userEntity.setName("test_user");
        reviewEntity.setUser(userEntity);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(100);
        bookEntity.setRating((short) 5);
        reviewEntity.setBook(bookEntity);

        ReviewUppDto reviewUppDto = new ReviewUppDto();
        reviewUppDto.setText("test");


        when(reviewMapper.reviewDtoToReview(any(), any())).thenReturn(new ReviewEntity());
        when(userRepository.findByLogin(any())).thenReturn(Optional.of(userEntity));
        when(reviewRepository.save(any())).thenReturn(reviewEntity);
        when(reviewRepository.findByBookId(any())).thenReturn(new ArrayList<>());
        when(reviewMapper.reviewDtoToReviewUppDto(any())).thenReturn(reviewUppDto);
        ReviewUppDto  reviewUppDtoNew =  reviewService.saveReview(new ReviewCreateDto(), "test");

        assertEquals("test", reviewUppDtoNew.getText());
    }
}

