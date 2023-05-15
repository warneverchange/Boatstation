package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "life_saving_device_log", schema = "boatstation", catalog = "")
public class LifeSavingDeviceLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "life_saving_device_id", nullable = false)
    private Long lifeSavingDeviceId;
    @Basic
    @Column(name = "watercraft_log_id", nullable = false)
    private Long watercraftLogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLifeSavingDeviceId() {
        return lifeSavingDeviceId;
    }

    public void setLifeSavingDeviceId(Long lifeSavingDeviceId) {
        this.lifeSavingDeviceId = lifeSavingDeviceId;
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
        LifeSavingDeviceLog that = (LifeSavingDeviceLog) o;
        return Objects.equals(id, that.id) && Objects.equals(lifeSavingDeviceId, that.lifeSavingDeviceId) && Objects.equals(watercraftLogId, that.watercraftLogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lifeSavingDeviceId, watercraftLogId);
    }
}
