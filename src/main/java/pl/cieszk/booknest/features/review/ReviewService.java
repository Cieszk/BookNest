package pl.cieszk.booknest.features.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.auth.UserRepository;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.auth.domain.enums.Role;
import pl.cieszk.booknest.features.book.BookRepository;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.review.domain.Review;
import pl.cieszk.booknest.features.review.domain.dto.ReviewRequestDto;
import pl.cieszk.booknest.features.review.domain.dto.ReviewResponseDto;
import pl.cieszk.booknest.features.review.mapper.ReviewMapper;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;

    public List<ReviewResponseDto> getAllReviewsForBookByBookId(Long id) {
        List<Review> reviews = reviewRepository.getReviewsByBook_BookId(id);
        return reviewMapper.toReviewResponseDtos(reviews);
    }

    public ReviewResponseDto addReview(ReviewRequestDto request) {
        Review review = reviewMapper.toReviewEntity(request);
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + request.getBookId() + " not found"));
        review.setBook(book);
        User user = userRepository.findById(Math.toIntExact(request.getUserId()))
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + request.getUserId() + " not found"));
        review.setUser(user);
        review = reviewRepository.save(review);
        return reviewMapper.toReviewResponseDto(review);
    }

    public ReviewResponseDto updateReview(ReviewRequestDto request, Long id) throws AccessDeniedException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id:" + id + " not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if(!review.getUser().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can only udpate your own reviews");
        }
        reviewMapper.updateEntity(review, request);
        review = reviewRepository.save(review);
        return reviewMapper.toReviewResponseDto(review);
    }

    public void deleteReview(Long id) throws AccessDeniedException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id:" + id + " not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if(!review.getUser().getUsername().equals(currentUsername) && !review.getUser().getRole().equals(Role.ADMIN)) {
            throw new AccessDeniedException("You can only delete your own reviews");
        }
        reviewRepository.delete(review);
    }
}
