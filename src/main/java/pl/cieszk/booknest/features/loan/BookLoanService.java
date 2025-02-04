package pl.cieszk.booknest.features.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cieszk.booknest.features.auth.AuthService;
import pl.cieszk.booknest.features.book.BookInstanceService;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.loan.domain.BookLoan;
import pl.cieszk.booknest.features.loan.domain.dto.BookLoanResponseDto;
import pl.cieszk.booknest.features.loan.mapper.BookLoanMapper;
import pl.cieszk.booknest.features.reservation.ReservationService;
import pl.cieszk.booknest.features.reservation.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookLoanService {
    private final BookLoanRepository loanRepository;
    private final BookInstanceService bookInstanceService;
    private final ReservationService reservationService;
    private final AuthService authService;
    private final BookLoanMapper bookLoanMapper;

    @Transactional
    public BookLoanResponseDto borrowBook(Long bookId, Long userId) {
        Optional<Reservation> reservationOpt = reservationService.findReservationByUserAndBook(bookId, userId);

        BookInstance bookInstance;
        if(reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            bookInstance = reservation.getBookInstance();

            reservationService.fulfillReservation(reservation.getReservationId());
        } else {
            bookInstance = bookInstanceService.findAvailableInstance(bookId);
        }

        BookLoan loan = BookLoan.builder()
                .loanDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusWeeks(2))
                .user(authService.getUserById(userId))
                .bookInstance(bookInstance)
                .build();
        loan = loanRepository.save(loan);
        return bookLoanMapper.toResponseDto(loan);
    }
}
