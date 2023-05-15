package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "booked_datetimes", schema = "boatstation", catalog = "")
public class BookedDatetimesView {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;
    @Basic
    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;
    @Basic
    @Column(name = "watercraft_log_id", nullable = true)
    private Long watercraftLogId;

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

    public Long getWatercraftLogId() {
        return watercraftLogId;
    }

    public void setWatercraftLogId(Long watercraftLogId) {
        this.watercraftLogId = watercraftLogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedDatetimesView that = (BookedDatetimesView) o;
        return Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo) && Objects.equals(watercraftLogId, that.watercraftLogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, watercraftLogId);
    }
}
