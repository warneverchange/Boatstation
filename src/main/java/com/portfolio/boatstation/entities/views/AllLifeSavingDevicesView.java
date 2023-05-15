package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "all_life_saving_devices", schema = "boatstation", catalog = "")
public class AllLifeSavingDevicesView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "_id", nullable = false)
    private Long _id;
    @Basic
    @Column(name = "id", nullable = true, length = 10)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllLifeSavingDevicesView that = (AllLifeSavingDevicesView) o;
        return Objects.equals(_id, that._id) && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, id, name);
    }
}
