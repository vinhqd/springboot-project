package com.example.service;


import com.example.dto.BrandDTO;

import java.util.List;

public interface IBrandService {

    List<BrandDTO> findAll();

    BrandDTO findOneById(long id);

    BrandDTO save(BrandDTO brandDTO);

    void delete(long[] ids);

}
