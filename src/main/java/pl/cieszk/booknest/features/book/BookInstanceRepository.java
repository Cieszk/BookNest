package pl.cieszk.booknest.features.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {

    List<BookInstance> findByBook_BookId(Long id);
    @Query("SELECT bi FROM BookInstance bi " +
            "WHERE bi.book.bookId = :bookId " +
            "AND bi.bookStatus = :status " +
            "AND bi.reservation IS NULL " +
            "AND NOT EXISTS (SELECT bl FROM BookLoan bl WHERE bl.bookInstance = bi AND bl.returnDate IS NULL) " +
            "ORDER BY bi.bookInstanceId ASC LIMIT 1")
    Optional<BookInstance> findFirstByBook_BookIdAndBookStatusAndReservationIsNullAndBookLoansIsEmpty(
            @Param("bookId") Long bookId,
            @Param("status") BookStatus status
    );

}
