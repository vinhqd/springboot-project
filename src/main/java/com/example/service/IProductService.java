package com.example.service;

import com.example.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll(Pageable pageable);

    long count();

}
