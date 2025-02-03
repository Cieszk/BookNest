package pl.cieszk.booknest.features.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.booknest.features.review.domain.Review;
import pl.cieszk.booknest.features.review.domain.dto.ReviewRequestDto;
import pl.cieszk.booknest.features.review.domain.dto.ReviewResponseDto;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/book/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByBook(@PathVariable Long id) {
        List<ReviewResponseDto> reviews = reviewService.getAllReviewsForBookByBookId(id);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/book/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto request) {
        ReviewResponseDto review = reviewService.addReview(request);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReviewResponseDto> updateReview(@RequestBody ReviewRequestDto requestDto, @PathVariable Long id) throws AccessDeniedException {
        ReviewResponseDto review = reviewService.updateReview(requestDto, id);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) throws AccessDeniedException {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
