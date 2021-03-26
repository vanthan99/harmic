package vanthan99.harmic.services;

import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.payloads.response.MessageResponse;

public interface DiscountDetailService {
    MessageResponse save(DiscountDetailDTO dto);
}
