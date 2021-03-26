package vanthan99.harmic.converters;

import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.entities.DiscountDetail;

@Component
public class DiscountDetailConverter {
    public DiscountDetail toEntity(DiscountDetailDTO dto){
        DiscountDetail discountDetail = new DiscountDetail();
        discountDetail.setKey(new DiscountDetailKey(dto.getDiscountId(),dto.getProductId()));
        discountDetail.setRate(dto.getRate());
        return discountDetail;
    }
}
