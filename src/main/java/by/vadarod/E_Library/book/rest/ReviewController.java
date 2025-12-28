package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "/changes/create_review")
    public void createReview(@RequestBody ReviewCreateDto reviewCreateDto) {
        reviewService.saveReview(reviewCreateDto);
    }

    @PostMapping(value = "/changes/upp_review")
    public void uppReview(@RequestBody ReviewUppDto reviewUppDto) {
        reviewService.saveUppReview(reviewUppDto);
    }

    @PostMapping(value = "/changes/dell_review")
    public void dellReview(@RequestBody ReviewUppDto reviewUppDto) {
        reviewService.dellById(reviewUppDto.getId());
    }

    @GetMapping(value = "/read/all_book_reviews/{id}")
    public List<ReviewUppDto> getAllReviews(@PathVariable long id) {
        return reviewService.getBookReviews(id);
    }
}
