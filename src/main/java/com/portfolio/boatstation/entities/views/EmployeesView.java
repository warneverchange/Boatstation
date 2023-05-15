package com.portfolio.boatstation.entities.views;

import jakarta.persistence.*;
import net.jcip.annotations.Immutable;

import java.util.Objects;

@Entity
@Table(name = "employees", schema = "boatstation", catalog = "")
@Immutable
public class EmployeesView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "client_data_id", nullable = false)
    private Long clientDataId;
    @Basic
    @Column(name = "job_title", nullable = false, length = 32)
    private String jobTitle;
    @Basic
    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;
    @Basic
    @Column(name = "patronymic", nullable = true, length = 256)
    private String patronymic;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesView that = (EmployeesView) o;
        return Objects.equals(id, that.id) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, firstName, lastName, patronymic, phoneNumber);
    }

    public Long getClientDataId() {
        return clientDataId;
    }

    public void setClientDataId(Long clientDataId) {
        this.clientDataId = clientDataId;
    }
}
