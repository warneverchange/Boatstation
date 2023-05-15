package com.portfolio.boatstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "rental_log", schema = "boatstation", catalog = "")
public class RentalLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;
    @Basic
    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;
    @Basic
    @Column(name = "client_data_id", nullable = false)
    private Long clientDataId;
    @Basic
    @Column(name = "watercraft_log_id", nullable = true)
    private Long watercraftLogId;
    @Basic
    @Column(name = "rental_status_id", nullable = false)
    private Long rentalStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public Long getClientDataId() {
        return clientDataId;
    }

    public void setClientDataId(Long clientDataId) {
        this.clientDataId = clientDataId;
    }

    public Long getWatercraftLogId() {
        return watercraftLogId;
    }

    public void setWatercraftLogId(Long watercraftLogId) {
        this.watercraftLogId = watercraftLogId;
    }

    public Long getRentalStatusId() {
        return rentalStatusId;
    }

    public void setRentalStatusId(Long rentalStatusId) {
        this.rentalStatusId = rentalStatusId;
    }

    private RentalLog() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalLog rentalLog = (RentalLog) o;
        return Objects.equals(id, rentalLog.id) && Objects.equals(dateFrom, rentalLog.dateFrom) && Objects.equals(dateTo, rentalLog.dateTo) && Objects.equals(clientDataId, rentalLog.clientDataId) && Objects.equals(watercraftLogId, rentalLog.watercraftLogId) && Objects.equals(rentalStatusId, rentalLog.rentalStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, clientDataId, watercraftLogId, rentalStatusId);
    }
}
