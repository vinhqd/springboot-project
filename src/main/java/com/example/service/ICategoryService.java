package com.example.service;

import com.example.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findOneById(long id);

    CategoryDTO save(CategoryDTO categoryDTO);

    void delete(long[] ids);

}
