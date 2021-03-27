package vanthan99.harmic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.entities.Review;
import vanthan99.harmic.payloads.response.ProductReviewResponse;
import vanthan99.harmic.repositories.ReviewRepository;
import vanthan99.harmic.repositories.UserRepository;

@Component
public class ProductReviewConverter {
    @Autowired
    UserRepository userRepository;

    public ProductReviewResponse toProductReviewResponse(Review review){
        ProductReviewResponse response = new ProductReviewResponse();
        response.setStar(review.getRate());
        response.setContent(review.getContent());
        response.setCreatedAt(review.getCreatedAt().toLocalDate());
        response.setEmailReviewer(review.getUser().getEmail());
        response.setNameReviewer(userRepository.getOne(review.getUser().getEmail()).getFullName());
        return response;
    }
}
