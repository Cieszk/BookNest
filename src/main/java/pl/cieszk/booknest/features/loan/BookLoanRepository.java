package pl.cieszk.booknest.features.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.loan.domain.BookLoan;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
}
