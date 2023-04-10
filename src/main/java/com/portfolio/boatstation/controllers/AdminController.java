package com.portfolio.boatstation.controllers;

import com.portfolio.boatstation.entities.views.RentalTableRecord;
import com.portfolio.boatstation.services.AdminPanel;
import com.portfolio.boatstation.services.AdminPanelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    private final AdminPanel adminPanel;

    @Autowired
    public AdminController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @GetMapping("/rental")
    public ResponseEntity<List<RentalTableRecord>> getRentalData() {
        return new ResponseEntity<>(adminPanel.getRentalTableData() ,HttpStatus.OK);
    }


}
