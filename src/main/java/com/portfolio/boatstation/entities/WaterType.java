package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "water_type", schema = "boatstation", catalog = "")
public class WaterType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaterType waterType = (WaterType) o;
        return Objects.equals(id, waterType.id) && Objects.equals(name, waterType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}