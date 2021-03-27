package vanthan99.harmic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/product")
@Api
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation(value = "Tìm sản phẩm theo Id")
    @GetMapping("/{productId}")
    public ResponseEntity<Object> findById(@PathVariable(value = "productId") UUID uuid){
        return ResponseEntity.ok(productService.findById(uuid));
    }

    @ApiOperation(value = "Danh sách sản phẩm hoạt động")
    @GetMapping(value = "/findByEnableTrue")
    public ResponseEntity<Object> findByEnableTrue(Pageable pageable){
        return ResponseEntity.ok(productService.findByEnableTrue(pageable));
    }

    @ApiOperation(value = "Danh sách sản phẩm không hoạt động")
    @GetMapping(value = "/findByEnableFalse")
    public ResponseEntity<Object> findByEnableFalse(Pageable pageable){
        return ResponseEntity.ok(productService.findByEnableTrue(pageable));
    }

    @ApiOperation(value = "Thay đổi trạng thái sản phẩm (tắt <-> bật)")
    @PutMapping(value = "/toggle/{productId}")
    public ResponseEntity<Object> toggle(@PathVariable(value = "productId")UUID productId){
        return ResponseEntity.ok(productService.toggle(productId));
    }

    @ApiOperation(value = "Thêm mới sản phẩm or sửa sản phẩm")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.save(dto));
    }

}
