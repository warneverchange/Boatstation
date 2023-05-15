package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;

import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "free_watercraft", schema = "boatstation", catalog = "")
public class FreeWatercraftView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "watercraft_number", nullable = false, length = 11)
    private String watercraftNumber;
    @Basic
    @Column(name = "water_type_id", nullable = false)
    private Long waterTypeId;
    @Basic
    @Column(name = "water_type", nullable = false, length = 32)
    private String waterType;
    @Basic
    @Column(name = "water_id", nullable = false)
    private Long waterId;
    @Basic
    @Column(name = "water", nullable = false, length = 45)
    private String water;
    @Basic
    @Column(name = "watercraft_type_id", nullable = false)
    private Long watercraftTypeId;
    @Basic
    @Column(name = "watercraft_type", nullable = false, length = 32)
    private String watercraftType;
    @Basic
    @Column(name = "brand_id", nullable = false)
    private Long brandId;
    @Basic
    @Column(name = "brand", nullable = false, length = 256)
    private String brand;
    @Basic
    @Column(name = "model_id", nullable = false)
    private Long modelId;
    @Basic
    @Column(name = "model", nullable = false, length = 256)
    private String model;
    @Basic
    @Column(name = "watercraft_id", nullable = false)
    private Long watercraftId;
    @Basic
    @Column(name = "watercraft_issue_year", nullable = false)
    private Year watercraftIssueYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWatercraftNumber() {
        return watercraftNumber;
    }

    public void setWatercraftNumber(String watercraftNumber) {
        this.watercraftNumber = watercraftNumber;
    }

    public Long getWaterTypeId() {
        return waterTypeId;
    }

    public void setWaterTypeId(Long waterTypeId) {
        this.waterTypeId = waterTypeId;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public Long getWaterId() {
        return waterId;
    }

    public void setWaterId(Long waterId) {
        this.waterId = waterId;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public Long getWatercraftTypeId() {
        return watercraftTypeId;
    }

    public void setWatercraftTypeId(Long watercraftTypeId) {
        this.watercraftTypeId = watercraftTypeId;
    }

    public String getWatercraftType() {
        return watercraftType;
    }

    public void setWatercraftType(String watercraftType) {
        this.watercraftType = watercraftType;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getWatercraftId() {
        return watercraftId;
    }

    public void setWatercraftId(Long watercraftId) {
        this.watercraftId = watercraftId;
    }

    public Year getWatercraftIssueYear() {
        return watercraftIssueYear;
    }

    public void setWatercraftIssueYear(Year watercraftIssueYear) {
        this.watercraftIssueYear = watercraftIssueYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeWatercraftView that = (FreeWatercraftView) o;
        return Objects.equals(id, that.id) && Objects.equals(watercraftNumber, that.watercraftNumber) && Objects.equals(waterTypeId, that.waterTypeId) && Objects.equals(waterType, that.waterType) && Objects.equals(waterId, that.waterId) && Objects.equals(water, that.water) && Objects.equals(watercraftTypeId, that.watercraftTypeId) && Objects.equals(watercraftType, that.watercraftType) && Objects.equals(brandId, that.brandId) && Objects.equals(brand, that.brand) && Objects.equals(modelId, that.modelId) && Objects.equals(model, that.model) && Objects.equals(watercraftId, that.watercraftId) && Objects.equals(watercraftIssueYear, that.watercraftIssueYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watercraftNumber, waterTypeId, waterType, waterId, water, watercraftTypeId, watercraftType, brandId, brand, modelId, model, watercraftId, watercraftIssueYear);
    }
}
