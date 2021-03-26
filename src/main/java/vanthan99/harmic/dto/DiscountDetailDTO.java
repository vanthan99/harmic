package vanthan99.harmic.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DiscountDetailDTO {
    private UUID productId;
    private UUID discountId;
    private Double rate;
}
