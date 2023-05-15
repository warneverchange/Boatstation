package com.portfolio.boatstation.repositories.water;

import com.portfolio.boatstation.entities.WaterType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface WaterTypeRepository extends Repository<WaterType, Long> {
    @Query(value = "select * from water_type", nativeQuery = true)
    Optional<List<WaterType>> getWaterTypes();
}
