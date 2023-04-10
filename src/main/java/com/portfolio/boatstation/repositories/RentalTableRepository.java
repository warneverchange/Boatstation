package com.portfolio.boatstation.repositories;

import com.portfolio.boatstation.entities.views.RentalTableRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RentalTableRepository extends Repository<RentalTableRecord, Long> {
    @Query (value = "select * from _rental_log", nativeQuery = true)
    List<RentalTableRecord> getRentalTableRecords();
}
