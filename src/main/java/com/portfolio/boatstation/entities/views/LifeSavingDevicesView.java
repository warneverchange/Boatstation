package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;
import net.jcip.annotations.Immutable;

import java.util.Objects;

@Entity
@Table(name = "_life_saving_devices", schema = "boatstation", catalog = "")
@Immutable
public class LifeSavingDevicesView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    @Basic
    @Column(name = "_watercraft_log_id", nullable = true)
    private Long watercraftLogId;
    @Basic
    @Column(name = "id", nullable = true, length = 10)
    private Long id;
    @Basic
    @Column(name = "life_saving_device_type", nullable = false, length = 64)
    private String lifeSavingDeviceType;

    public Long get_id() {
        return _id;
    }

    public Long getWatercraftLogId() {
        return watercraftLogId;
    }


    public Long getId() {
        return id;
    }


    public String getLifeSavingDeviceType() {
        return lifeSavingDeviceType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifeSavingDevicesView that = (LifeSavingDevicesView) o;
        return Objects.equals(_id, that._id) && Objects.equals(watercraftLogId, that.watercraftLogId) && Objects.equals(id, that.id) && Objects.equals(lifeSavingDeviceType, that.lifeSavingDeviceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, watercraftLogId, id, lifeSavingDeviceType);
    }
}
