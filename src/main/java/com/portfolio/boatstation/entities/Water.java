package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Water {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "water_type_id", nullable = false)
    private Long waterTypeId;

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

    public Long getWaterTypeId() {
        return waterTypeId;
    }

    public void setWaterTypeId(Long waterTypeId) {
        this.waterTypeId = waterTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Water water = (Water) o;
        return Objects.equals(id, water.id) && Objects.equals(name, water.name) && Objects.equals(waterTypeId, water.waterTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, waterTypeId);
    }
}
