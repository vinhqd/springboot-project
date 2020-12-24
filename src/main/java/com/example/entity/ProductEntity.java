package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private double price;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brandid", nullable = false)
    private BrandEntity brand;

}
