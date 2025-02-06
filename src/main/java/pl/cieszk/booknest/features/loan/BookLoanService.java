package pl.cieszk.booknest.features.loan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cieszk.booknest.features.auth.AuthService;
import pl.cieszk.booknest.features.book.BookInstanceRepository;
import pl.cieszk.booknest.features.book.BookInstanceService;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;
import pl.cieszk.booknest.features.loan.domain.BookLoan;
import pl.cieszk.booknest.features.loan.domain.dto.BookLoanResponseDto;
import pl.cieszk.booknest.features.loan.mapper.BookLoanMapper;
import pl.cieszk.booknest.features.reservation.ReservationService;
import pl.cieszk.booknest.features.reservation.domain.Reservation;
import pl.cieszk.booknest.features.shared.BookUserDto;

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
    private final BookInstanceRepository bookInstanceRepository;

    @Transactional
    public BookLoanResponseDto borrowBook(BookUserDto bookUserDto) {
        Long bookId = bookUserDto.getBookId();
        Long userId = bookUserDto.getUserId();
        BookStatus bookStatus = BookStatus.ACTIVE;
        Optional<Reservation> reservationOpt = reservationService.findReservationByUserAndBook(bookId, userId, bookStatus);

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

    @Transactional
    public void returnLoan(Long loanId) {
        BookLoan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new IllegalStateException("Loan is already returned");
        }

        loan.setReturnDate(LocalDateTime.now());
        loanRepository.save(loan);

        BookInstance bookInstance = loan.getBookInstance();
        if (bookInstance != null) {
            bookInstance.setBookStatus(BookStatus.ACTIVE);
            bookInstanceRepository.save(bookInstance);
        }
    }
}
