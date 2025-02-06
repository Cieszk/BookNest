package pl.cieszk.booknest.features.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;
import pl.cieszk.booknest.features.reservation.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.user.userId = :userId " +
            "AND r.bookInstance.book.bookId = :bookId " +
            "AND r.bookInstance.bookStatus = :bookStatus " +
            "ORDER BY r.reservationId ASC LIMIT 1")
    Optional<Reservation> findFirstByUser_UserIdAndBookInstance_Book_BookIdAndBookInstance_BookStatus(
            @Param("userId") Long userId,
            @Param("bookId") Long bookId,
            @Param("bookStatus") BookStatus bookStatus);

    boolean existsByUserAndBookInstance_BookAndDueDateAfter(User user, Book book, LocalDateTime date);
}
