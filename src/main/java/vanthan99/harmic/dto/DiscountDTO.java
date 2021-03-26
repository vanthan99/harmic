package vanthan99.harmic.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DiscountDTO {
    private UUID id;
    private String name;
    private String note;
    private LocalDate startDay;
    private LocalDate endDay;
}
