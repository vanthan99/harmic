package vanthan99.harmic.payloads.request;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class DiscountNewRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String note;

    @Future
    private LocalDate startDay;


    private LocalDate endDay;
}
