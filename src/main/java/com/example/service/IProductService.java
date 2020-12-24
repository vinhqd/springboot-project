package com.example.service;

import com.example.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();

}
