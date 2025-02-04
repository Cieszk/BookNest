package pl.cieszk.booknest.features.author.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {
    private Long authorId;
    private String firstName;
    private String lastName;
    private String nationality;
    private String biography;
    private Set<BookListDto> books;
}
