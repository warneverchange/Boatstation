package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.Immutable;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "_rental_log", schema = "boatstation")
@Getter
@Setter
@EqualsAndHashCode
@Immutable
public class RentalTableView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;
    @Basic
    @Column(name = "patronymic", length = 256)
    private String patronymic;
    @Basic
    @Column(name = "passport_data", nullable = false, length = 10)
    private String passportData;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;
    @Basic
    @Column(name = "date_from", nullable = false)
    private Timestamp dateFrom;
    @Basic
    @Column(name = "date_to", nullable = false)
    private Timestamp dateTo;
    @Basic
    @Column(name = "watercraft_number", nullable = false, length = 11)
    private String watercraftNumber;
    @Basic
    @Column(name = "rental_status", nullable = false, length = 32)
    private String rentalStatus;
}
