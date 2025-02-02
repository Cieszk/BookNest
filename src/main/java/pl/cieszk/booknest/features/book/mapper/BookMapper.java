package pl.cieszk.booknest.features.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.auth.domain.dto.UserDto;
import pl.cieszk.booknest.features.author.domain.Author;
import pl.cieszk.booknest.features.author.domain.dto.AuthorSummaryDto;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;
import pl.cieszk.booknest.features.book.domain.dto.BookRequestDto;
import pl.cieszk.booknest.features.book.domain.dto.BookResponseDto;
import pl.cieszk.booknest.features.category.domain.Category;
import pl.cieszk.booknest.features.category.domain.dto.CategorySummaryDto;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherSummaryDto;
import pl.cieszk.booknest.features.review.domain.Review;
import pl.cieszk.booknest.features.review.domain.dto.ReviewSummaryDto;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "authors", source = "authors")
    BookResponseDto toBookResponseDto(Book book);

    PublisherSummaryDto toPublisherSummaryDto(Publisher publisher);

    CategorySummaryDto toCategorySummaryDto(Category category);

    @Mapping(source = "user", target = "user")
    ReviewSummaryDto toReviewSummaryDto(Review review);

    UserDto toUserDto(User user);

    // Mapping for a single Author to AuthorSummaryDto.
    default AuthorSummaryDto toAuthorSummaryDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorSummaryDto dto = new AuthorSummaryDto();
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        return dto;
    }

    // Explicit mapping for the authors collection.
    default Set<AuthorSummaryDto> mapAuthors(Set<Author> authors) {
        if (authors == null) {
            return null;
        }
        return authors.stream()
                .map(this::toAuthorSummaryDto)
                .collect(Collectors.toSet());
    }

    Set<CategorySummaryDto> toCategorySummaryDtos(Set<Category> categories);

    Set<ReviewSummaryDto> toReviewSummaryDtos(Set<Review> reviews);
}