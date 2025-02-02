package pl.cieszk.booknest.features.review.domain.dto;

import lombok.Data;
import pl.cieszk.booknest.features.auth.domain.dto.UserDto;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDto {
    private Long reviewId;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private UserDto user;
    private Long bookId; // Only include the book ID
    private String bookTitle;
}
