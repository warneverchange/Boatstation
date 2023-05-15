package com.portfolio.boatstation.services.employees;

import com.portfolio.boatstation.entities.EmployeeJobTitle;
import com.portfolio.boatstation.entities.views.EmployeesView;

import java.util.List;

public interface EmployeesService {
    void addNewEmployee(Long clientDataId, Long employeeJobTitle);

    List<EmployeesView> getEmployeesData();

    void fireEmployee(Long employeeId);

    List<EmployeeJobTitle> getEmployeesJobTitles();
}
