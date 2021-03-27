package vanthan99.harmic.payloads.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductReviewResponse {
    private Long star;
    private LocalDate createdAt;
    private String emailReviewer;
    private String nameReviewer;
    private String content;
}
