package pl.cieszk.booknest.features.reservation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cieszk.booknest.features.reservation.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public Optional<Reservation> findReservationByUserAndBook(Long userId, Long bookId) {
        return reservationRepository.findByUser_UserIdAndBookInstance_Book_BookIdAndBookInstance_BookStatus_Active(userId, bookId);
    }

    @Transactional
    public void fulfillReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        reservation.setReturnDate(LocalDateTime.now());
        reservationRepository.save(reservation);
    }
}
