package vanthan99.harmic.services;

import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.payloads.response.MessageResponse;

import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    Set<CategoryDTO> findByEnableTrue();
    Set<CategoryDTO> findByEnableFalse();
    MessageResponse save(CategoryDTO categoryDTO);
    MessageResponse toggleEnable(UUID id);
}
