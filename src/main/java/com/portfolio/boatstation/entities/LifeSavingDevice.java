package com.portfolio.boatstation.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "life_saving_device", schema = "boatstation", catalog = "")
public class LifeSavingDevice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "life_saving_device_type_id", nullable = false)
    private Long lifeSavingDeviceTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLifeSavingDeviceTypeId() {
        return lifeSavingDeviceTypeId;
    }

    public void setLifeSavingDeviceTypeId(Long lifeSavingDeviceTypeId) {
        this.lifeSavingDeviceTypeId = lifeSavingDeviceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifeSavingDevice that = (LifeSavingDevice) o;
        return Objects.equals(id, that.id) && Objects.equals(lifeSavingDeviceTypeId, that.lifeSavingDeviceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lifeSavingDeviceTypeId);
    }
}
