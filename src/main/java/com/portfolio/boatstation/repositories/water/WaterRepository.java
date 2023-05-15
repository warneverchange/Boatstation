package com.portfolio.boatstation.repositories.water;

import com.portfolio.boatstation.entities.Water;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface WaterRepository extends Repository<Water, Long> {
    @Query(value = "select * from water", nativeQuery = true)
    Optional<List<Water>> getWaters();
}
