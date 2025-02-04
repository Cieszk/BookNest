package pl.cieszk.booknest.features.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cieszk.booknest.features.publisher.domain.Publisher;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    boolean existsByName(String name);
}
