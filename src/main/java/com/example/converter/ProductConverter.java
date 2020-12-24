package com.example.converter;

import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ModelConverter converter;

    public ProductDTO productConverter(ProductEntity entity) {
        ProductDTO dto = converter.toDTO(entity, ProductDTO.class);
        return dto;
    }

}
