package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "briefing_log", schema = "boatstation", catalog = "")
public class BriefingLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @Basic
    @Column(name = "briefing_type_id", nullable = false)
    private Long briefingTypeId;
    @Basic
    @Column(name = "rental_log_id", nullable = false)
    private Long rentalLogId;
    @Basic
    @Column(name = "client_data_id", nullable = false)
    private Long clientDataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getBriefingTypeId() {
        return briefingTypeId;
    }

    public void setBriefingTypeId(Long briefingTypeId) {
        this.briefingTypeId = briefingTypeId;
    }

    public Long getRentalLogId() {
        return rentalLogId;
    }

    public void setRentalLogId(Long rentalLogId) {
        this.rentalLogId = rentalLogId;
    }

    public Long getClientDataId() {
        return clientDataId;
    }

    public void setClientDataId(Long clientDataId) {
        this.clientDataId = clientDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BriefingLog that = (BriefingLog) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(briefingTypeId, that.briefingTypeId) && Objects.equals(rentalLogId, that.rentalLogId) && Objects.equals(clientDataId, that.clientDataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, briefingTypeId, rentalLogId, clientDataId);
    }
}
