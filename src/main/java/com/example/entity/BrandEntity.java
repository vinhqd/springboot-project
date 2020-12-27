package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
public class BrandEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "nameunsigned")
    private String nameUnsigned;

    @Column
    private String code;

    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getNameUnsigned() {
        return nameUnsigned;
    }

    public void setNameUnsigned(String nameUnsigned) {
        this.nameUnsigned = nameUnsigned;
    }
}
