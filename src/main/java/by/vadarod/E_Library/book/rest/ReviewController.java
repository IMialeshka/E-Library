package by.vadarod.E_Library.book.rest;

import by.vadarod.E_Library.book.dto.ReviewCreateDto;
import by.vadarod.E_Library.book.dto.ReviewUppDto;
import by.vadarod.E_Library.book.entity.ReviewEntity;
import by.vadarod.E_Library.book.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "/limited/create_review")
    public void createReview(@Validated @RequestBody ReviewCreateDto reviewCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
        reviewService.saveReview(reviewCreateDto, userDetails.getUsername());
    }

    @PostMapping(value = "/limited/upp_review")
    public void uppReview(@Validated @RequestBody ReviewUppDto reviewUppDto, @AuthenticationPrincipal UserDetails userDetails) {
        reviewService.saveUppReview(reviewUppDto, userDetails.getUsername());
    }

    @PostMapping(value = "/limited/dell_review")
    public void dellReview(@RequestBody ReviewUppDto reviewUppDto, @AuthenticationPrincipal UserDetails userDetails) {
        reviewService.dellById(reviewUppDto.getId(), userDetails.getUsername());
    }

    @GetMapping(value = "/for_all/all_book_reviews/{id}")
    public List<ReviewUppDto> getAllReviews(@PathVariable long id) {
        return reviewService.getBookReviews(id);
    }
}
