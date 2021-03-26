package vanthan99.harmic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.services.CategoryService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(categoryService.findByEnableTrue());
    }

    @GetMapping(value = "/disable")
    public ResponseEntity<Object> findByDisable() {
        return ResponseEntity.ok(categoryService.findByEnableFalse());
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<Object> save(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.save(dto));
    }

    @PutMapping(value = "/toggle/{categoryId}")
    public ResponseEntity<Object> edit(@PathVariable(value = "categoryId") UUID id) {
        return ResponseEntity.ok(categoryService.toggleEnable(id));
    }
}
