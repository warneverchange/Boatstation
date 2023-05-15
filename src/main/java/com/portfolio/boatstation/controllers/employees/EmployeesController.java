package com.portfolio.boatstation.controllers.employees;


import com.portfolio.boatstation.entities.EmployeeJobTitle;
import com.portfolio.boatstation.entities.views.EmployeesView;
import com.portfolio.boatstation.services.employees.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new/{clientDataId}/job-title/{jobTitleId}")
    public void addNewEmployee(
            @PathVariable("clientDataId") Long clientDataId,
            @PathVariable("jobTitleId") Long jobTitleId) {
        employeesService.addNewEmployee(clientDataId,jobTitleId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{employeeId}/fire")
    public void fireEmployee(@PathVariable("employeeId") Long employeeId) {
        employeesService.fireEmployee(employeeId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EmployeesView>> getEmployeesData() {
        return ResponseEntity.ok(employeesService.getEmployeesData());
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/job_titles")
    public ResponseEntity<List<EmployeeJobTitle>> getEmployeesJobTitle() {
        return ResponseEntity.ok(employeesService.getEmployeesJobTitles());
    }
}
