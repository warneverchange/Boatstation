package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.time.Year;
import java.util.Objects;

@Entity
public class Watercraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "issue_year", nullable = false)
    private Year issueYear;
    @Basic
    @Column(name = "model_id", nullable = false)
    private Long modelId;
    @Basic
    @Column(name = "watercraft_type_id", nullable = false)
    private Long watercraftTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Year issueYear) {
        this.issueYear = issueYear;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getWatercraftTypeId() {
        return watercraftTypeId;
    }

    public void setWatercraftTypeId(Long watercraftTypeId) {
        this.watercraftTypeId = watercraftTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watercraft that = (Watercraft) o;
        return Objects.equals(id, that.id) && Objects.equals(issueYear, that.issueYear) && Objects.equals(modelId, that.modelId) && Objects.equals(watercraftTypeId, that.watercraftTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueYear, modelId, watercraftTypeId);
    }
}
