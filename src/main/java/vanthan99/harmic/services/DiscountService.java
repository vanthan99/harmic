package vanthan99.harmic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vanthan99.harmic.dto.DiscountDTO;
import vanthan99.harmic.payloads.response.MessageResponse;

import java.time.LocalDate;
import java.util.UUID;

public interface DiscountService {
    Page<DiscountDTO> findByEnableTrue(Pageable pageable);
    Page<DiscountDTO> findByEnableFalse(Pageable pageable);
    MessageResponse save(DiscountDTO dto);
    MessageResponse toggleEnable(UUID uuid);
    MessageResponse extendEndDay(UUID disId, LocalDate date);
}
