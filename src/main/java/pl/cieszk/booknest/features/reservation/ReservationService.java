package pl.cieszk.booknest.features.reservation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cieszk.booknest.features.auth.UserRepository;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.book.BookInstanceRepository;
import pl.cieszk.booknest.features.book.BookRepository;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;
import pl.cieszk.booknest.features.reservation.domain.Reservation;
import pl.cieszk.booknest.features.reservation.domain.dto.CreateReservationDto;
import pl.cieszk.booknest.features.reservation.domain.dto.ReservationResponseDto;
import pl.cieszk.booknest.features.reservation.mapper.ReservationMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final ReservationMapper reservationMapper;

    @Transactional(readOnly = true)
    public Optional<Reservation> findReservationByUserAndBook(Long userId, Long bookId, BookStatus bookStatus) {
        return reservationRepository.findFirstByUser_UserIdAndBookInstance_Book_BookIdAndBookInstance_BookStatus(userId, bookId, bookStatus);
    }

    @Transactional
    public void fulfillReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        reservation.setReturnDate(LocalDateTime.now());
        reservationRepository.save(reservation);
    }

    @Transactional
    public ReservationResponseDto createReservation(CreateReservationDto createReservationDto) {
        Book book = bookRepository.findById(createReservationDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        User user = userRepository.findById(Math.toIntExact(createReservationDto.getUserId()))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (hasActiveReservationForBook(user, book)) {
            throw new IllegalStateException("User already has an active reservation for this book");
        }

        BookInstance availableInstance = bookInstanceRepository
                .findFirstByBook_BookIdAndBookStatusAndReservationIsNullAndBookLoansIsEmpty(
                        book.getBookId(),
                        BookStatus.ACTIVE
                )
                .orElseThrow(() -> new IllegalStateException("No available copies of this book"));

        Reservation reservation = Reservation.builder()
                .reservationDate(createReservationDto.getReservationDate() != null
                        ? createReservationDto.getReservationDate()
                        : LocalDateTime.now())
                .dueDate(createReservationDto.getDueDate() != null
                        ? createReservationDto.getDueDate()
                        : LocalDateTime.now().plusDays(3))
                .user(user)
                .bookInstance(availableInstance)
                .build();

        reservation = reservationRepository.save(reservation);
        return reservationMapper.toResponseDto(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        if (reservation.getReturnDate() != null) {
            throw new IllegalStateException("Reservation is already canceled or fulfilled");
        }

        reservation.setReturnDate(LocalDateTime.now());
        reservationRepository.save(reservation);
    }

    private boolean hasActiveReservationForBook(User user, Book book) {
        return reservationRepository.existsByUserAndBookInstance_BookAndDueDateAfter(
                user,
                book,
                LocalDateTime.now()
        );
    }
}
