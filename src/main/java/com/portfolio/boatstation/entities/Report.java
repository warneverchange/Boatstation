package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "scan", nullable = false)
    private byte[] scan;
    @Basic
    @Column(name = "briefing_log_id", nullable = true)
    private Long briefingLogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }

    public Long getBriefingLogId() {
        return briefingLogId;
    }

    public void setBriefingLogId(Long briefingLogId) {
        this.briefingLogId = briefingLogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Arrays.equals(scan, report.scan) && Objects.equals(briefingLogId, report.briefingLogId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, briefingLogId);
        result = 31 * result + Arrays.hashCode(scan);
        return result;
    }
}
