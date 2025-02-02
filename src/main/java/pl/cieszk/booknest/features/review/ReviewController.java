package pl.cieszk.booknest.features.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cieszk.booknest.features.review.domain.dto.ReviewResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByBook(@PathVariable Long id) {
        List<ReviewResponseDto> reviews = reviewService.getAllReviewsForBookByBookId(id);
        return ResponseEntity.ok(reviews);
    }
}
