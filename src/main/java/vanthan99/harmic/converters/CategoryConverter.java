package vanthan99.harmic.converters;

import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.entities.Category;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryConverter {
    public CategoryDTO toDTO(Category category){
        return new CategoryDTO(category.getId(),category.getName());
    }

    public Set<CategoryDTO> toDTOs(Set<Category> categories){
        Set<CategoryDTO> dtos = new HashSet<>();
        categories.forEach(category -> dtos.add(this.toDTO(category)));
        return dtos;
    }
    public Category toEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setId(categoryDTO.getId());
        return category;
    }
    public Category toEntity(CategoryDTO dto, Category entity){
        entity.setName(dto.getName());
        return entity;
    }
}
