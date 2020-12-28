package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {

    @Autowired
    private ModelMapper mapper;

    public <T, K> T toDTO(K entity, Class<T> dtoClass) {
        T dto = mapper.map(entity, dtoClass);
        return dto;
    }

    public <T, K> T toEntity(K dto, Class<T> entityClass) {
        T entity = mapper.map(dto, entityClass);
        mapper.map(dto, dto);
        return entity;
    }

    public <T, K> T toEntity(K dto, T entity) {
        mapper.map(dto, entity);
        return entity;
    }

}
