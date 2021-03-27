package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.DiscountDetailConverter;
import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.entities.Discount;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.repositories.DiscountDetailRepository;
import vanthan99.harmic.repositories.DiscountRepository;
import vanthan99.harmic.repositories.ProductRepository;
import vanthan99.harmic.services.DiscountDetailService;

import java.util.UUID;

@Service
public class DiscountDetailServiceImpl implements DiscountDetailService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    DiscountDetailRepository discountDetailRepository;

    @Autowired
    DiscountDetailConverter converter;

    @Override
    public MessageResponse save(DiscountDetailDTO dto,boolean isAdd) {
        Product product = productRepository.findById(dto.getProductId()).orElse(null);
        Discount discount = discountRepository.findById(dto.getDiscountId()).orElse(null);
        if (product == null) return new MessageResponse("already not exists product");
        if (discount == null) return new MessageResponse("already not exists discount");

        if (isAdd){
            if (discountDetailRepository.existsByProductAndDiscount(product,discount))
                return new MessageResponse("called. already exists discount name with this product");
            discountDetailRepository.save(converter.toEntity(dto));
            return new MessageResponse("successfully");
        }else {
            DiscountDetail discountDetail = discountDetailRepository.findByProductAndDiscount(product,discount);
            discountDetailRepository.save(converter.toEntity(dto,discountDetail));
            return new MessageResponse("update rate success!");
        }


    }

    @Override
    public MessageResponse delete(UUID productId, UUID discountId) {
        Product product = productRepository.findById(productId).orElse(null);
        Discount discount = discountRepository.findById(discountId).orElse(null);
        if (product == null || discount == null)
            return new MessageResponse("product id or discount id invalid");

        boolean result = discountDetailRepository.deleteByProductAndDiscount(product,discount);
        if (result) return new MessageResponse("success");
        return new MessageResponse("fails");
    }

}
