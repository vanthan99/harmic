package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.ProductConverter;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.repositories.CategoryRepository;
import vanthan99.harmic.repositories.ProductRepository;
import vanthan99.harmic.services.ProductService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductConverter converter;

    @Autowired
    CategoryRepository catRepo;

    @Override
    public MessageResponse save(ProductDTO dto) {
        // check category id valid
        if (!catRepo.existsById(dto.getCatId())) {
            return new MessageResponse("already exists category");
        }
        // truong hop them  moi
        if (dto.getId() == null) {
            productRepo.save(converter.toEntity(dto));
            return new MessageResponse("Success!");
        }
        // truong hop cap nhat
        else {
            // check product id exists
            Product product = productRepo.findById(dto.getId()).orElse(null);
            if (product == null)
                return new MessageResponse("already exists product");
            productRepo.save(converter.toEntity(dto,product));
            return new MessageResponse("Success");
        }
    }

    @Override
    public MessageResponse toggle(UUID productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product == null) {
            return new MessageResponse("already exists id!");
        }
        boolean isEnable = product.isEnable();
        MessageResponse message = new MessageResponse();
        if (isEnable) {
            message.setMessage("disable successfully!");
        } else message.setMessage("enable successfully!");
        productRepo.save(product);
        return message;
    }

    @Override
    public Page<ProductDTO> findByEnableTrue(Pageable pageable) {
        return productRepo.findByEnableTrue(pageable).map(product -> converter.toDTO(product));
    }

    @Override
    public Page<ProductDTO> findByEnableFalse(Pageable pageable) {
        return productRepo.findByEnableFalse(pageable).map(product -> converter.toDTO(product));
    }
}
