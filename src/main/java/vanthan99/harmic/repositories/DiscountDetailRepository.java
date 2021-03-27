package vanthan99.harmic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vanthan99.harmic.entities.Discount;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.entities.Product;

import java.util.List;
import java.util.UUID;

public interface DiscountDetailRepository extends JpaRepository<DiscountDetail, UUID> {
    boolean existsByProductAndDiscount(Product product, Discount discount);

    DiscountDetail findByProductAndDiscount(Product product, Discount discount);

    boolean deleteByProductAndDiscount(Product product, Discount discount);

    List<DiscountDetail> findByProduct(Product product);
}
