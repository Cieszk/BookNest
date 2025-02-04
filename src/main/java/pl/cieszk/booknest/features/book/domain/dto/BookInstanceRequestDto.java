package pl.cieszk.booknest.features.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceRequestDto {
    private Long bookId;
    private BookStatus bookStatus;
}
