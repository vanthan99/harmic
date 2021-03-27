package vanthan99.harmic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.entities.Review;
import vanthan99.harmic.payloads.response.ProductResponse;
import vanthan99.harmic.repositories.CategoryRepository;
import vanthan99.harmic.repositories.DiscountDetailRepository;
import vanthan99.harmic.repositories.DiscountRepository;
import vanthan99.harmic.repositories.ReviewRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    @Autowired
    CategoryRepository catRepo;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    DiscountDetailRepository discountDetailRepository;

    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setQuantity(product.getQuantity());
        return dto;
    }

    public Product toEntity(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setCategory(catRepo.findById(dto.getCatId()).orElse(null));
        product.setPrice(dto.getPrice());
        return product;
    }

    public Product toEntity(ProductDTO dto, Product product){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setCategory(catRepo.findById(dto.getCatId()).orElse(null));
        product.setPrice(dto.getPrice());
        return product;
    }

    public ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getId());
        response.setCatId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setImage(product.getImage());
        response.setQuantity(product.getQuantity());
        response.setInitPrice(product.getPrice());
        response.setStarRate(getStarRate(product));
        response.setDiscountPrice(getDiscountPrice(product));
        return response;
    }

    public Long getStarRate(Product product) {
        List<Review> reviewList = reviewRepository.findByEnableTrueAndProduct(product);
        if (reviewList.isEmpty()) return null;
        LongSummaryStatistics statistics = reviewList.stream().collect(Collectors.summarizingLong(Review::getRate));

        if ((statistics.getAverage () - ((long) statistics.getAverage()) >= 0.5)){
            return ((long)statistics.getAverage()) +1;
        }
        else return (long) statistics.getAverage();
    }

    public Long getDiscountPrice(Product product) {
        LocalDate toDay = LocalDate.now();
        List<DiscountDetail> detailList = discountDetailRepository.findByProduct(product);
        DiscountDetail discountDetail = detailList
                .stream()
                .filter(detail -> !toDay.isBefore(detail.getDiscount().getStartDay()) && toDay.isBefore(detail.getDiscount().getEndDay()))
                .max(Comparator.comparing(DiscountDetail::getRate)).orElse(null);
        if (discountDetail == null) return null;
        return Math.round(product.getPrice() * (1 - discountDetail.getRate()));
    }
}
