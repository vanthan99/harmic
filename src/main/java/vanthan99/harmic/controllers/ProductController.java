package vanthan99.harmic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/findByEnableTrue")
    public ResponseEntity<Object> findByEnableTrue(Pageable pageable){
        return ResponseEntity.ok(productService.findByEnableTrue(pageable));
    }

    @GetMapping(value = "/findByEnableFalse")
    public ResponseEntity<Object> findByEnableFalse(Pageable pageable){
        return ResponseEntity.ok(productService.findByEnableTrue(pageable));
    }

    @PutMapping(value = "/toggle/{productId}")
    public ResponseEntity<Object> toggle(@PathVariable(value = "productId")UUID productId){
        return ResponseEntity.ok(productService.toggle(productId));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.save(dto));
    }

}
