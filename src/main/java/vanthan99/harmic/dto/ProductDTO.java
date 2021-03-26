package vanthan99.harmic.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    private UUID id;
    private UUID catId;
    private String name;
    private String description;
    private String image;
    private Long quantity;
}
