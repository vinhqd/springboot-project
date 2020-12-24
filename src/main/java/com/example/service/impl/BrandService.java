package com.example.service.impl;

import com.example.converter.ModelConverter;
import com.example.dto.BrandDTO;
import com.example.entity.BrandEntity;
import com.example.repository.BrandRepository;
import com.example.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelConverter converter;


    @Override
    public List<BrandDTO> findAll() {
        List<BrandEntity> entities = brandRepository.findAll();
        List<BrandDTO> result = entities.stream()
                .map(item -> converter.toDTO(item, BrandDTO.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public BrandDTO findOneById(long id) {
        BrandEntity entity = brandRepository.findById(id).get();
        return converter.toDTO(entity, BrandDTO.class);
    }

    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        BrandEntity entity = brandRepository.save(converter.toEntity(brandDTO, BrandEntity.class));
        return converter.toDTO(entity, BrandDTO.class);
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(item -> brandRepository.deleteById(item));
    }

}
