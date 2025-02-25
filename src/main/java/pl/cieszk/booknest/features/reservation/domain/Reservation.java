package pl.cieszk.booknest.features.reservation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.auth.domain.User;
import pl.cieszk.booknest.features.book.domain.BookInstance;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    @Column
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "book_instance_id", nullable = false, unique = true)
    private BookInstance bookInstance;
}
