package com.portfolio.boatstation.services;

import com.portfolio.boatstation.entities.views.RentalTableRecord;

import java.util.List;

public interface AdminPanel {
    List<RentalTableRecord> getRentalTableData();
    void updateRentalTableRecord(RentalTableRecord rentalTableRecord);
    List<RentalTableRecord> getExpireRentalTableRecords();
}
