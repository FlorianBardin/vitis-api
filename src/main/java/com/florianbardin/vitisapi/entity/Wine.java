package com.florianbardin.vitisapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @Digits(fraction = 0, integer = 4)
    @PositiveOrZero
    private Integer vintage;

    @NotBlank
    private String color;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer stock;

    @ManyToOne
    @NotNull
    private Winery winery;

    public Wine() {
    }

    public Wine(Integer id, String name, Integer vintage, String color, Double price, Integer stock, Winery winery) {
        this.id = id;
        this.name = name;
        this.vintage = vintage;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.winery = winery;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVintage() {
        return vintage;
    }

    public void setVintage(Integer vintage) {
        this.vintage = vintage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }


}
