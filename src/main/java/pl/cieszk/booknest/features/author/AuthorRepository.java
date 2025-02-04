package pl.cieszk.booknest.features.author;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cieszk.booknest.features.author.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
