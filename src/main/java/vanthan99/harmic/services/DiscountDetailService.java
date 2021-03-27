package vanthan99.harmic.services;

import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.payloads.response.MessageResponse;

import java.util.UUID;

public interface DiscountDetailService {
    MessageResponse save(DiscountDetailDTO dto,boolean isAdd);
    MessageResponse delete(UUID productId, UUID discountId);
}
