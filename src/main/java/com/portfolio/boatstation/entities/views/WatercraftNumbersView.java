package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "watercraft_numbers", schema = "boatstation", catalog = "")
public class WatercraftNumbersView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "watercraft_number", nullable = false, length = 11)
    private String watercraftNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatercraftNumbersView that = (WatercraftNumbersView) o;
        return Objects.equals(id, that.id) && Objects.equals(watercraftNumber, that.watercraftNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watercraftNumber);
    }
}
