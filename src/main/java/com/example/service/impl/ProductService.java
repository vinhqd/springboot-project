package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.converter.ProductConverter;
import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductEntity> entities = productRepository.findAll(pageable).toList();
        return entities.stream()
                .map(item -> productConverter.productConverter(item)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return productRepository.count();
    }
}