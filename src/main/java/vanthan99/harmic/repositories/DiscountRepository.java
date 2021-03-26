package vanthan99.harmic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Discount;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    Page<Discount> findByEnableTrue(Pageable pageable);
    Page<Discount> findByEnableFalse(Pageable pageable);
}
