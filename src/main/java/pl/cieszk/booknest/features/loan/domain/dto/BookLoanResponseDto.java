package pl.cieszk.booknest.features.loan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.auth.domain.dto.UserDto;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceSummaryDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanResponseDto {
    private Long bookLoanId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private LocalDateTime dueDate;
    private Double fineAmount;
    private Integer renewCount;
    private UserDto userDto;
    private BookInstanceSummaryDto bookInstance;
}
