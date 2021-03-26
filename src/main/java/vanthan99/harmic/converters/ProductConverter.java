package vanthan99.harmic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.DiscountDetail;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.entities.Review;
import vanthan99.harmic.payloads.request.ProductRequest;
import vanthan99.harmic.repositories.CategoryRepository;

import java.util.DoubleSummaryStatistics;

@Component
public class ProductConverter {
    @Autowired
    CategoryRepository catRepo;

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
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setCategory(catRepo.getOne(dto.getId()));
        return product;
    }

    public Product toEntity(ProductDTO dto, Product product){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setCategory(catRepo.getOne(dto.getId()));
        return product;
    }
}
