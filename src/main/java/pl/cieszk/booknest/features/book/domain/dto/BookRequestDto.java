package pl.cieszk.booknest.features.book.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookRequestDto {
    private String title;
    private String genre;
    private Integer publishYear;
    private String isbn;
    private String language;
    private Integer pageCount;
    private String description;
    private Long publisherId;
    private Set<Long> authorIds;
    private Set<Long> categoryIds;
}
