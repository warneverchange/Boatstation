package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;
import net.jcip.annotations.Immutable;

import java.time.Year;
import java.util.Objects;

@Entity
@Immutable
public class WatercraftsView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "water_type", nullable = false, length = 32)
    private String waterType;
    @Basic
    @Column(name = "water_name", nullable = false, length = 45)
    private String waterName;
    @Basic
    @Column(name = "watercraft_type_name", nullable = false, length = 32)
    private String watercraftTypeName;
    @Basic
    @Column(name = "brand_name", nullable = false, length = 256)
    private String brandName;
    @Basic
    @Column(name = "model_name", nullable = false, length = 256)
    private String modelName;
    @Basic
    @Column(name = "issue_year", nullable = false)
    private Year issueYear;
    @Basic
    @Column(name = "technical_condition", nullable = false, length = 32)
    private String technicalCondition;

    @Basic
    @Column(name = "watercraft_number", nullable = false, length = 11)
    private String watercraftNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getWaterName() {
        return waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName;
    }

    public String getWatercraftTypeName() {
        return watercraftTypeName;
    }

    public void setWatercraftTypeName(String watercraftTypeName) {
        this.watercraftTypeName = watercraftTypeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Year getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Year issueYear) {
        this.issueYear = issueYear;
    }

    public String getTechnicalCondition() {
        return technicalCondition;
    }

    public void setTechnicalCondition(String technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatercraftsView that = (WatercraftsView) o;
        return Objects.equals(id, that.id) && Objects.equals(waterType, that.waterType) && Objects.equals(waterName, that.waterName) && Objects.equals(watercraftTypeName, that.watercraftTypeName) && Objects.equals(brandName, that.brandName) && Objects.equals(modelName, that.modelName) && Objects.equals(issueYear, that.issueYear) && Objects.equals(technicalCondition, that.technicalCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waterType, waterName, watercraftTypeName, brandName, modelName, issueYear, technicalCondition);
    }

    public String getWatercraftNumber() {
        return watercraftNumber;
    }

    public void setWatercraftNumber(String watercraftNumber) {
        this.watercraftNumber = watercraftNumber;
    }
}
