package vanthan99.harmic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.payloads.response.MessageResponse;

import java.util.UUID;

public interface ProductService {
    MessageResponse save(ProductDTO dto);
    MessageResponse toggle(UUID productId);
    Page<ProductDTO> findByEnableTrue(Pageable pageable);
    Page<ProductDTO> findByEnableFalse(Pageable pageable);
}
