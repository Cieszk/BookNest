package pl.cieszk.booknest.features.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.dto.BookListDto;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT new pl.cieszk.booknest.features.book.domain.dto.BookListDto(" +
            "b.bookId, b.title, b.genre, b.publishYear, b.isbn, b.language, p.name, " +
            "CAST(FUNCTION('STRING_AGG', CONCAT(a.firstName, ' ', a.lastName), ', ') AS string)) " +
            "FROM Book b " +
            "JOIN b.publisher p " +
            "LEFT JOIN b.authors a " +
            "GROUP BY b.bookId, b.title, b.genre, b.publishYear, b.isbn, b.language, p.name")
    List<BookListDto> findAllBooksForList();
}
