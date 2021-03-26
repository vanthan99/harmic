package vanthan99.harmic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByEnableTrue(Pageable pageable);
    Page<Product> findByEnableFalse(Pageable pageable);
}
