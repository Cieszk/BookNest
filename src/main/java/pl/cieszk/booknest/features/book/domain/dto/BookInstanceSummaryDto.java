package pl.cieszk.booknest.features.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInstanceSummaryDto {
    private Long bookInstanceId;
    private BookStatus bookStatus;
}
