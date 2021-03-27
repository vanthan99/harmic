package vanthan99.harmic.payloads.request;

import lombok.Data;
import vanthan99.harmic.dto.ProductDTO;

@Data
public class ProductRequest {
    private ProductDTO productDTO;
    private Long starRate;
    private Long discountPrice;
}
