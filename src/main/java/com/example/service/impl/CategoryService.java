package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import com.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelConverter converter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryDTO> results = entities
                .stream().map(item -> converter.toDTO(item, CategoryDTO.class)).collect(Collectors.toList());
        return results;
    }

    @Override
    public CategoryDTO findOneById(long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        CategoryDTO dto = converter.toDTO(entity.get(), CategoryDTO.class);
        return dto;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity entity = categoryRepository.save(converter.toEntity(categoryDTO, CategoryEntity.class));
        return converter.toDTO(entity, CategoryDTO.class);
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            categoryRepository.deleteById(id);
        }
    }
}
