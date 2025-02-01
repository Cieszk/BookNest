package pl.cieszk.booknest.features.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cieszk.booknest.features.auth.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
