package com.portfolio.boatstation.services.employees;


import com.portfolio.boatstation.entities.EmployeeJobTitle;
import com.portfolio.boatstation.entities.views.EmployeesView;
import com.portfolio.boatstation.repositories.client.ClientDataRepository;
import com.portfolio.boatstation.repositories.employees.EmployeesJobTitleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    private final ClientDataRepository clientDataRepository;
    private final EmployeesJobTitleRepository employeesJobTitleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeesServiceImpl(ClientDataRepository clientDataRepository, EmployeesJobTitleRepository employeesJobTitleRepository) {
        this.clientDataRepository = clientDataRepository;
        this.employeesJobTitleRepository = employeesJobTitleRepository;
    }


    @Override
    public void addNewEmployee(Long clientDataId, Long employeeJobTitle) {
        clientDataRepository.markAsEmployee(clientDataId, employeeJobTitle);
    }

    @Override
    public List<EmployeesView> getEmployeesData() {
        return entityManager.createNativeQuery("select * from employees", EmployeesView.class).getResultList();
    }

    @Override
    public void fireEmployee(Long employeeId) {
        clientDataRepository.fireEmployee(employeeId);
    }

    @Override
    public List<EmployeeJobTitle> getEmployeesJobTitles() {
        return employeesJobTitleRepository.getEmployeesJobTitles().orElseThrow();
    }
}
