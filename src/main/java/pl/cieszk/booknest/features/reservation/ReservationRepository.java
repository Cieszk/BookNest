package pl.cieszk.booknest.features.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.reservation.domain.Reservation;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByUser_UserIdAndBookInstance_Book_BookIdAndBookInstance_BookStatus_Active(Long userId, Long bookId);
}
