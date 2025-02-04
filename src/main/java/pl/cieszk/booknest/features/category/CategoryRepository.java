package pl.cieszk.booknest.features.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.booknest.features.category.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
