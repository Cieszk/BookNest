package pl.cieszk.booknest.features.book.domain.dto;

import lombok.Data;
import pl.cieszk.booknest.features.author.domain.dto.AuthorSummaryDto;
import pl.cieszk.booknest.features.category.domain.dto.CategorySummaryDto;
import pl.cieszk.booknest.features.publisher.domain.dto.PublisherSummaryDto;
import pl.cieszk.booknest.features.review.domain.dto.ReviewSummaryDto;

import java.util.Set;

@Data
public class BookResponseDto {
    private Long bookId;
    private String title;
    private String genre;
    private Integer publishYear;
    private String isbn;
    private String language;
    private Integer pageCount;
    private String description;
    private PublisherSummaryDto publisher;
    private Set<AuthorSummaryDto> authors;
    private Set<CategorySummaryDto> categories;
    private Set<ReviewSummaryDto> reviews;
}
