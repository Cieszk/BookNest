package pl.cieszk.booknest.features.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.book.domain.BookInstance;

import java.util.List;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {

    List<BookInstance> findByBook_BookId(Long id);
}
