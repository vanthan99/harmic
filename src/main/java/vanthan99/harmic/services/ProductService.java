package vanthan99.harmic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.payloads.response.ProductResponse;
import vanthan99.harmic.payloads.response.ProductReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    MessageResponse save(ProductDTO dto);
    MessageResponse toggle(UUID productId);
    Page<ProductResponse> findByEnableTrue(Pageable pageable);
    Page<ProductResponse> findByEnableFalse(Pageable pageable);
    ProductResponse findById(UUID uuid);
    List<ProductReviewResponse> listReview(Product product);
}
