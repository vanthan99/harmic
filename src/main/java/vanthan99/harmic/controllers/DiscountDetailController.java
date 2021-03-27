package vanthan99.harmic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.services.DiscountDetailService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/discountDetail")
@Api
public class DiscountDetailController {
    @Autowired
    DiscountDetailService discountDetailService;

    @ApiOperation(value = "Thêm mới khuyến mãi chi tiết")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody DiscountDetailDTO dto) {
        return ResponseEntity.ok(discountDetailService.save(dto, true));
    }

    @ApiOperation(value = "Chỉnh sửa tỉ lệ khuyến mãi")
    @PutMapping
    public ResponseEntity<Object> updateRate(@RequestBody DiscountDetailDTO dto) {
        return ResponseEntity.ok(discountDetailService.save(dto, false));
    }

    @ApiOperation(value = "Xóa khuyến mãi")
    @DeleteMapping(value = "/{productId}/{discountId}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "productId") UUID productId,
            @PathVariable(value = "discountId") UUID discountId
    ) {
        return ResponseEntity.ok(discountDetailService.delete(productId, discountId));
    }


}
