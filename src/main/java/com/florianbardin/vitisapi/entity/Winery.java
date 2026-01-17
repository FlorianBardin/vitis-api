package com.florianbardin.vitisapi.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String region;

    private String address;

    @OneToMany
    private List<Wine> wine;

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

    public List<Wine> getWine() {
        return wine;
    }

    public void setWine(List<Wine> wine) {
        this.wine = wine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Winery winery = (Winery) o;
        return Objects.equals(id, winery.id) && Objects.equals(name, winery.name) && Objects.equals(region, winery.region) && Objects.equals(address, winery.address) && Objects.equals(wine, winery.wine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, address, wine);
    }
}
