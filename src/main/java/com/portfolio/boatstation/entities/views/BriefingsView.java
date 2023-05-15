package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "briefings", schema = "boatstation", catalog = "")
public class BriefingsView {
    @Basic
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "rental_log_id", nullable = true)
    private Long rentalLogId;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @Basic
    @Column(name = "briefing_type", nullable = false, length = 32)
    private String briefingType;
    @Basic
    @Column(name = "employee_first_name", nullable = false, length = 256)
    private String employeeFirstName;
    @Basic
    @Column(name = "employee_last_name", nullable = false, length = 256)
    private String employeeLastName;
    @Basic
    @Column(name = "employee_patronymic", nullable = true, length = 256)
    private String employeePatronymic;
    @Basic
    @Column(name = "employee_phone_number", nullable = false, length = 13)
    private String employeePhoneNumber;
    @Basic
    @Column(name = "client_first_name", nullable = false, length = 256)
    private String clientFirstName;
    @Basic
    @Column(name = "client_last_name", nullable = false, length = 256)
    private String clientLastName;
    @Basic
    @Column(name = "client_patronymic", nullable = true, length = 256)
    private String clientPatronymic;
    @Basic
    @Column(name = "client_phone_number", nullable = false, length = 13)
    private String clientPhoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRentalLogId() {
        return rentalLogId;
    }

    public void setRentalLogId(Long rentalLogId) {
        this.rentalLogId = rentalLogId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getBriefingType() {
        return briefingType;
    }

    public void setBriefingType(String briefingType) {
        this.briefingType = briefingType;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeePatronymic() {
        return employeePatronymic;
    }

    public void setEmployeePatronymic(String employeePatronymic) {
        this.employeePatronymic = employeePatronymic;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPatronymic() {
        return clientPatronymic;
    }

    public void setClientPatronymic(String clientPatronymic) {
        this.clientPatronymic = clientPatronymic;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BriefingsView that = (BriefingsView) o;
        return Objects.equals(id, that.id) && Objects.equals(rentalLogId, that.rentalLogId) && Objects.equals(date, that.date) && Objects.equals(briefingType, that.briefingType) && Objects.equals(employeeFirstName, that.employeeFirstName) && Objects.equals(employeeLastName, that.employeeLastName) && Objects.equals(employeePatronymic, that.employeePatronymic) && Objects.equals(employeePhoneNumber, that.employeePhoneNumber) && Objects.equals(clientFirstName, that.clientFirstName) && Objects.equals(clientLastName, that.clientLastName) && Objects.equals(clientPatronymic, that.clientPatronymic) && Objects.equals(clientPhoneNumber, that.clientPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalLogId, date, briefingType, employeeFirstName, employeeLastName, employeePatronymic, employeePhoneNumber, clientFirstName, clientLastName, clientPatronymic, clientPhoneNumber);
    }
}
