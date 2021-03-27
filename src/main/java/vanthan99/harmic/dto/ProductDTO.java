package vanthan99.harmic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID id;
    private UUID catId;
    private String name;
    private String description;
    private String image;
    private Long quantity;
    private Long price;
}
