package com.example.repository;

import com.example.entity.BrandEntity;
import com.example.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findOneByCode(String code);

}
