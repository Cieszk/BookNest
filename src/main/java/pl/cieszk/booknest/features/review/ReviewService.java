package pl.cieszk.booknest.features.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.review.domain.Review;
import pl.cieszk.booknest.features.review.domain.dto.ReviewResponseDto;
import pl.cieszk.booknest.features.review.mapper.ReviewMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewResponseDto> getAllReviewsForBookByBookId(Long id) {
        List<Review> reviews = reviewRepository.getReviewsByBook_BookId(id);
        return reviewMapper.toReviewResponseDtos(reviews);
    }
}
