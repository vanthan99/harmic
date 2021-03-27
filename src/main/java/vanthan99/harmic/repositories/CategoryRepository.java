package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Category;

import java.util.Set;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Set<Category> findByEnableTrue();

    Set<Category> findByEnableFalse();

    boolean existsByName(String name);

    Category findByName(String name);
}
