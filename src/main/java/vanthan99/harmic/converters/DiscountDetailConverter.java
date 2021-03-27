package vanthan99.harmic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.repositories.DiscountRepository;
import vanthan99.harmic.repositories.ProductRepository;

@Component
public class DiscountDetailConverter {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DiscountRepository discountRepository;

    public DiscountDetail toEntity(DiscountDetailDTO dto){
        DiscountDetail discountDetail = new DiscountDetail();
        discountDetail.setProduct(productRepository.findById(dto.getProductId()).orElseThrow(() -> new RuntimeException("product not found!")));
        discountDetail.setDiscount(discountRepository.findById(dto.getDiscountId()).orElseThrow(() -> new RuntimeException("discount not found!")));
        discountDetail.setRate(dto.getRate());
        return discountDetail;
    }

    public DiscountDetail toEntity(DiscountDetailDTO dto,DiscountDetail detail){
        detail.setRate(dto.getRate());
        return detail;
    }
}
