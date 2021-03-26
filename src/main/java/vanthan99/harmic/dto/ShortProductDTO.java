package vanthan99.harmic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortProductDTO {
    private UUID id;
    private String name;
    private Long unitPrice;
    private Long discountPrice;
    private Long rateStar;
}
