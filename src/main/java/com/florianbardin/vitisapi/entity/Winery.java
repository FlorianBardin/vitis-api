package com.florianbardin.vitisapi.entity;

import jakarta.persistence.*;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Winery winery = (Winery) o;
        return Objects.equals(id, winery.id) && Objects.equals(name, winery.name) && Objects.equals(region, winery.region) && Objects.equals(address, winery.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region, address);
    }
}
