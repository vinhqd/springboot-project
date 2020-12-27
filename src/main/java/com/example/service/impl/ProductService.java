package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.converter.ProductConverter;
import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.repository.BrandRepository;
import com.example.repository.CategoryRepository;
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
    private AmazonClient amazonClient;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelConverter converter;

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductEntity> entities = productRepository.findAll(pageable).toList();
        return entities.stream()
                .map(item -> converter.toDTO(item, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public ProductDTO findOneById(long id) {
        return converter.toDTO(productRepository.findById(id).get(), ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity entity = converter.toEntity(productDTO, ProductEntity.class);
        entity.setBrand(brandRepository.findOneByCode(productDTO.getBrandCode()).get());
        entity.setCategory(categoryRepository.findOneByCode(productDTO.getCategoryCode()).get());
        if (productDTO.getMultipartFile().getOriginalFilename() == null || !productDTO.getMultipartFile().getOriginalFilename().equals("")) {
            entity.setThumbnail(amazonClient.uploadFile(productDTO.getMultipartFile()));
        }
        return converter.toDTO(productRepository.save(entity), ProductDTO.class);
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            productRepository.deleteById(id);
        }
    }
}
