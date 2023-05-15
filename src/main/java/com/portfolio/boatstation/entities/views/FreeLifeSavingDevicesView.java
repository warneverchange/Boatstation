package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;
import net.jcip.annotations.Immutable;
import org.springframework.context.annotation.Import;

import java.util.Objects;

@Entity
@Table(name = "_free_life_saving_devices", schema = "boatstation", catalog = "")
@Immutable
public class FreeLifeSavingDevicesView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "_id", nullable = false)
    private Long _id;

    @Basic
    @Column(name = "id", nullable = true, length = 10)
    private String  id;
    @Basic
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
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
        FreeLifeSavingDevicesView that = (FreeLifeSavingDevicesView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long get_id() {
        return _id;
    }


}
