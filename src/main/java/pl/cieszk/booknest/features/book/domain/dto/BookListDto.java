package pl.cieszk.booknest.features.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookListDto {
    private Long bookId;
    private String title;
    private String genre;
    private Integer publishYear;
    private String isbn;
    private String language;
    private String publisherName;
    private String authorNames;

    public BookListDto(
            Long bookId,
            String title,
            String genre,
            Integer publishYear,
            String isbn,
            String language,
            String publisherName,
            String authorNames
    ) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.isbn = isbn;
        this.language = language;
        this.publisherName = publisherName;
        this.authorNames = authorNames;
    }
}
