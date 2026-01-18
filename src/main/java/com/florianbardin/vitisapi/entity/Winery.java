package com.florianbardin.vitisapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String region;

    private String address;

    @OneToMany(mappedBy = "winery")
    private List<Wine> wines;

    public Winery() {
    }

    public Winery(Integer id, String name, String region, String address) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.address = address;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public void setWines(List<Wine> wine) {
        this.wines = wine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Winery winery = (Winery) o;
        return Objects.equals(id, winery.id) && Objects.equals(name, winery.name) && Objects.equals(region, winery.region) && Objects.equals(address, winery.address) && Objects.equals(wines, winery.wines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, address, wines);
    }
}
