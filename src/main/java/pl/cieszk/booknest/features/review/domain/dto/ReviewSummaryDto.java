package pl.cieszk.booknest.features.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.cieszk.booknest.features.auth.domain.dto.UserDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewSummaryDto {
    private Long reviewId;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private UserDto user;
}
