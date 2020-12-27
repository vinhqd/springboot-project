package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "nameunsigned")
    private String nameUnsigned;

    @Column
    private double price;

    @Column(name = "thumbnail")
    private String thumbnail;


    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "descriptionunsigned", columnDefinition = "TEXT")
    private String descriptionUnsigned;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brandid", nullable = false)
    private BrandEntity brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
