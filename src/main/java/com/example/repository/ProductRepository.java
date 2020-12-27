package com.example.repository;

import com.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("FROM ProductEntity p " +
            "INNER JOIN CategoryEntity cate ON cate.id = p.category.id " +
            "INNER JOIN BrandEntity brand ON brand.id = p.brand.id " +
            "WHERE p.nameUnsigned LIKE %?1% OR cate.nameUnsigned LIKE %?1% OR brand.nameUnsigned LIKE %?1%")
    List<ProductEntity> findAllByName(String name);

}
