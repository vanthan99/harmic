package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.DiscountDetail;

import java.util.UUID;

public interface DiscountDetailRepository extends JpaRepository<DiscountDetail, UUID> {
    boolean existsByProductAndDiscount();
}
