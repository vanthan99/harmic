package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.CategoryConverter;
import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.entities.Category;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.repositories.CategoryRepository;
import vanthan99.harmic.services.CategoryService;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Override
    public Set<CategoryDTO> findByEnableTrue() {
        return categoryConverter.toDTOs(categoryRepository.findByEnableTrue());
    }

    @Override
    public Set<CategoryDTO> findByEnableFalse() {
        return categoryConverter.toDTOs(categoryRepository.findByEnableFalse());
    }

    @Override
    public MessageResponse save(CategoryDTO categoryDTO) {
        // truong hop them moi
        if (categoryDTO.getId() == null){
            if (!categoryRepository.existsByName(categoryDTO.getName())){
                categoryRepository.save(categoryConverter.toEntity(categoryDTO));
                return new MessageResponse("New successfully added");
            }
            return new MessageResponse("already exists name!");

        }
        // truong hop chinh sua
        else {
            if (categoryRepository.existsByName(categoryDTO.getName())){
                return new MessageResponse("already exists name!");
            }
            if (!categoryRepository.existsById(categoryDTO.getId())){
                return new MessageResponse("already exists id!");
            }
            Category oldCategory = categoryRepository.getOne(categoryDTO.getId());
            categoryRepository.save(categoryConverter.toEntity(categoryDTO,oldCategory));
            return new MessageResponse("successfully edited");
        }
    }

    @Override
    public MessageResponse toggleEnable(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null){
            return new MessageResponse("already exists id");
        }
        MessageResponse message = new MessageResponse();
        if (category.isEnable()){
            message.setMessage("disable successfully!");
        }
        else {
            message.setMessage("eable successfully!");
        }
        category.setEnable(!category.isEnable());
        categoryRepository.save(category);
        return message;
    }
}
