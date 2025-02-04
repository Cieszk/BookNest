package pl.cieszk.booknest.features.book.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;
import pl.cieszk.booknest.features.loan.domain.BookLoan;
import pl.cieszk.booknest.features.reservation.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookInstanceId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookStatus bookStatus;

    @OneToOne(mappedBy = "bookInstance", cascade = CascadeType.ALL)
    private Reservation reservation;

    @OneToMany(mappedBy = "bookInstance", cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans;

    public boolean isAvailable() {
        return !isReserved() && !isLoaned() && bookStatus == BookStatus.ACTIVE;
    }

    public boolean isReserved() {
        return reservation != null
                && reservation.getReservationDate().isBefore(LocalDateTime.now())
                && (reservation.getReturnDate() == null
                    || reservation.getReturnDate().isAfter(LocalDateTime.now()));
    }

    public boolean isLoaned() {
        return bookLoans != null
                && bookLoans.stream().anyMatch(loan ->
                loan.getReturnDate() == null
                    || loan.getDueDate().isAfter(LocalDateTime.now()));
    }

}
