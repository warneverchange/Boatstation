package com.portfolio.boatstation.repositories.employees;

import com.portfolio.boatstation.entities.EmployeeJobTitle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface EmployeesJobTitleRepository extends Repository<EmployeeJobTitle, Long> {
    @Query(value = "select * from employee_job_title", nativeQuery = true)
    Optional<List<EmployeeJobTitle>> getEmployeesJobTitles();
}
