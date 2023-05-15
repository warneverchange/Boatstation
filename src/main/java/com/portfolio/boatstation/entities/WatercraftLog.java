package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "watercraft_log", schema = "boatstation", catalog = "")
public class WatercraftLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "watercraft_number", nullable = false, length = 11)
    private String watercraftNumber;
    @Basic
    @Column(name = "water_id", nullable = true)
    private Long waterId;
    @Basic
    @Column(name = "technical_condition_id", nullable = false)
    private Long technicalConditionId;
    @Basic
    @Column(name = "watercraft_id", nullable = false)
    private Long watercraftId;

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

    public Long getWaterId() {
        return waterId;
    }

    public void setWaterId(Long waterId) {
        this.waterId = waterId;
    }

    public Long getTechnicalConditionId() {
        return technicalConditionId;
    }

    public void setTechnicalConditionId(Long technicalConditionId) {
        this.technicalConditionId = technicalConditionId;
    }

    public Long getWatercraftId() {
        return watercraftId;
    }

    public void setWatercraftId(Long watercraftId) {
        this.watercraftId = watercraftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatercraftLog that = (WatercraftLog) o;
        return Objects.equals(id, that.id) && Objects.equals(watercraftNumber, that.watercraftNumber) && Objects.equals(waterId, that.waterId) && Objects.equals(technicalConditionId, that.technicalConditionId) && Objects.equals(watercraftId, that.watercraftId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watercraftNumber, waterId, technicalConditionId, watercraftId);
    }
}
