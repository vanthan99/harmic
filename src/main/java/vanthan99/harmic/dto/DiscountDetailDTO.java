package vanthan99.harmic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetailDTO {
    private UUID productId;
    private UUID discountId;
    private Double rate;
}
