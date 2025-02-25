package pl.cieszk.booknest.features.review.domain.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private Integer rating;
    private String comment;
    private Long userId;
    private Long bookId;
}
