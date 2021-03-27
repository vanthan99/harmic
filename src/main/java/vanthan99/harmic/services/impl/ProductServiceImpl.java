package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.ProductConverter;
import vanthan99.harmic.converters.ProductReviewConverter;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.entities.Review;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.payloads.response.ProductResponse;
import vanthan99.harmic.payloads.response.ProductReviewResponse;
import vanthan99.harmic.repositories.CategoryRepository;
import vanthan99.harmic.repositories.DiscountDetailRepository;
import vanthan99.harmic.repositories.ProductRepository;
import vanthan99.harmic.repositories.ReviewRepository;
import vanthan99.harmic.services.ProductService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductConverter converter;

    @Autowired
    CategoryRepository catRepo;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    DiscountDetailRepository discountDetailRepository;

    @Autowired
    ProductReviewConverter productReviewConverter;

    @Autowired
    ProductConverter productConverter;

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
            productRepo.save(converter.toEntity(dto, product));
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
    public Page<ProductResponse> findByEnableTrue(Pageable pageable) {
        return productRepo.findByEnableTrue(pageable).map(product -> productConverter.toProductResponse(product));
    }

    @Override
    public Page<ProductResponse> findByEnableFalse(Pageable pageable) {
        return productRepo.findByEnableFalse(pageable).map(product -> productConverter.toProductResponse(product));
    }

    @Override
    public ProductResponse findById(UUID uuid) {
        Product product = productRepo.findById(uuid).orElse(null);
        if (product == null) return null;
        return productConverter.toProductResponse(product);
    }

    @Override
    public List<ProductReviewResponse> listReview(Product product) {
        return reviewRepository.findByEnableTrueAndProduct(product)
                .stream().map(review -> productReviewConverter.toProductReviewResponse(review)).collect(Collectors.toList());
    }


}
