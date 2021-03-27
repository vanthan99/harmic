package vanthan99.harmic.payloads.response;

import lombok.Data;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.Category;

import java.util.UUID;

@Data
public class ProductResponse {
    private UUID productId;
    private UUID catId;
    private String categoryName;
    private String name;
    private String description;
    private String image;
    private Long quantity;
    private Long starRate;
    private Long initPrice;
    private Long discountPrice;
}
