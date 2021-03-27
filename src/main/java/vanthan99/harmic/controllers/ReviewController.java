package vanthan99.harmic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vanthan99.harmic.converters.ProductReviewConverter;
import vanthan99.harmic.entities.Product;
import vanthan99.harmic.repositories.ProductRepository;
import vanthan99.harmic.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/review")
public class ReviewController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> findByProduct(@PathVariable(value = "id")UUID productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not exists!"));
        return ResponseEntity.ok(productService.listReview(product));
    }
}
