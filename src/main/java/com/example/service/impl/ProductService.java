package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.dto.ProductDTO;
import com.example.entity.BrandEntity;
import com.example.entity.ProductEntity;
import com.example.repository.BrandRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
import com.example.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public List<ProductDTO> findByName(String name) {
        List<ProductEntity> entities = productRepository.findAllByName(VNCharacterUtils.removeAccent(name));
        return entities.stream().map(item -> converter.toDTO(item, ProductDTO.class)).collect(Collectors.toList());
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
        entity.setNameUnsigned(VNCharacterUtils.removeAccent(productDTO.getName()));
        if (productDTO.getMultipartFile().getOriginalFilename() == null || !productDTO.getMultipartFile().getOriginalFilename().equals("")) {
            entity.setThumbnail(amazonClient.uploadFile(productDTO.getMultipartFile()));
        }
        if (productDTO.getId() != null) {
            ProductEntity oldEntity = productRepository.findById(productDTO.getId()).get();
            entity = converter.toEntity(entity, oldEntity);
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
