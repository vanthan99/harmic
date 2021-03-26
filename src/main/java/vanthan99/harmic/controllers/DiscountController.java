package vanthan99.harmic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanthan99.harmic.dto.DiscountDTO;
import vanthan99.harmic.services.DiscountService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping(value = "/findByEnableTrue")
    public ResponseEntity<Object> findEnable(Pageable pageable) {
        return ResponseEntity.ok(discountService.findByEnableTrue(pageable));
    }

    @GetMapping(value = "/findByEnableFalse")
    public ResponseEntity<Object> findFalse(Pageable pageable) {
        return ResponseEntity.ok(discountService.findByEnableFalse(pageable));
    }

    @PutMapping(value = "/toggle/{discountId}")
    public ResponseEntity<Object> toggle(@PathVariable(value = "discountId") UUID discountId) {
        return ResponseEntity.ok(discountService.toggleEnable(discountId));
    }

    @PutMapping(value = "/extendEndDay/{disId}/{date}")
    public ResponseEntity<Object> extendEndDay(
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            @PathVariable(value = "date") LocalDate date,
            @PathVariable(value = "disId") UUID disId) {
        return ResponseEntity.ok(discountService.extendEndDay(disId,date));
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<Object> save(@RequestBody @Valid DiscountDTO dto){
        return ResponseEntity.ok(discountService.save(dto));
    }
}
