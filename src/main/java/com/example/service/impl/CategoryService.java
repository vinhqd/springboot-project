package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.dto.CategoryDTO;
import com.example.entity.BrandEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ProductEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.ICategoryService;
import com.example.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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
        CategoryEntity entity = converter.toEntity(categoryDTO, CategoryEntity.class);
        if (categoryDTO.getId() != null) {
            CategoryEntity oldEntity = categoryRepository.findById(categoryDTO.getId()).get();
            entity = converter.toEntity(entity, oldEntity);
        }
        entity.setNameUnsigned(VNCharacterUtils.removeAccent(entity.getName()));
        return converter.toDTO(categoryRepository.save(entity), CategoryDTO.class);
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(item -> {
            productRepository.deleteAllByCategory(categoryRepository.getOne(item));
            categoryRepository.deleteById(item);
        });
    }
}
