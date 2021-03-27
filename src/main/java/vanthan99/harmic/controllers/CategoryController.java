package vanthan99.harmic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.services.CategoryService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/category")
@Api
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Danh sách danh mục")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(categoryService.findByEnableTrue());
    }

    @GetMapping(value = "/disable")
    @ApiOperation(value = "Danh sách danh mục không hoạt động")
    public ResponseEntity<Object> findByDisable() {
        return ResponseEntity.ok(categoryService.findByEnableFalse());
    }

    @ApiOperation(value = "Thêm or Sửa tên danh mục")
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<Object> save(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.save(dto));
    }

    @ApiOperation(value = "Thay đổi trạng thái hoạt động (bật <-> tắt)")
    @PutMapping(value = "/toggle/{categoryId}")
    public ResponseEntity<Object> edit(@PathVariable(value = "categoryId") UUID id) {
        return ResponseEntity.ok(categoryService.toggleEnable(id));
    }
}
