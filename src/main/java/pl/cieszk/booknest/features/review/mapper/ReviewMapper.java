package pl.cieszk.booknest.features.review.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.auth.domain.dto.UserDto;
import pl.cieszk.booknest.features.review.domain.Review;
import pl.cieszk.booknest.features.review.domain.dto.ReviewRequestDto;
import pl.cieszk.booknest.features.review.domain.dto.ReviewResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "bookId", source = "book.bookId")
    @Mapping(target = "bookTitle", source = "book.title")
    ReviewResponseDto toReviewResponseDto(Review review);

    List<ReviewResponseDto> toReviewResponseDtos(List<Review> reviews);

    UserDto toUserDto(User user);

    Review toReviewEntity(ReviewRequestDto reviewRequestDto);
}
