package com.portfolio.boatstation.services;

import com.portfolio.boatstation.entities.views.RentalTableRecord;
import com.portfolio.boatstation.repositories.RentalTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPanelImpl implements AdminPanel {
    private RentalTableRepository rentalTableRepository;

    @Autowired
    public AdminPanelImpl(RentalTableRepository rentalTableRepository) {
        this.rentalTableRepository =rentalTableRepository;
    }


    @Override
    public List<RentalTableRecord> getRentalTableData() {
        return rentalTableRepository.getRentalTableRecords();
    }

    @Override
    public void updateRentalTableRecord(RentalTableRecord rentalTableRecord) {

    }

    @Override
    public List<RentalTableRecord> getExpireRentalTableRecords() {
        return null;
    }
}
