package vanthan99.harmic.payloads.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequest {
    private UUID catId;
    private String name;
    private String description;
    private String image;
    private Long quantity;
}
