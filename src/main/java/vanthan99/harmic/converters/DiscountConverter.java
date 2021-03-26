package vanthan99.harmic.converters;

import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.DiscountDTO;
import vanthan99.harmic.entities.Discount;

@Component
public class DiscountConverter {
    public DiscountDTO toDTO(Discount discount){
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setName(discount.getName());
        dto.setNote(discount.getNote());
        dto.setStartDay(discount.getStartDay());
        dto.setEndDay(discount.getEndDay());
        return dto;
    }

    public Discount toEntity(DiscountDTO dto){
        Discount discount = new Discount();
        discount.setName(dto.getName());
        discount.setNote(dto.getNote());
        discount.setStartDay(dto.getStartDay());
        discount.setEndDay(dto.getEndDay());
        return discount;
    }

    public Discount toEntity(DiscountDTO dto,Discount discount){
        discount.setName(dto.getName());
        discount.setNote(dto.getNote());
        discount.setStartDay(dto.getStartDay());
        discount.setEndDay(dto.getEndDay());
        return discount;
    }
}
