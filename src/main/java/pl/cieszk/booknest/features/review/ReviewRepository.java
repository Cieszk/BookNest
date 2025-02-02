package pl.cieszk.booknest.features.review;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cieszk.booknest.features.review.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getReviewsByBook_BookId(Long id);
}
