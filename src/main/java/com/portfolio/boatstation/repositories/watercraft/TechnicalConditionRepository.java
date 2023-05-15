package com.portfolio.boatstation.repositories.watercraft;

import com.portfolio.boatstation.entities.TechnicalCondition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface TechnicalConditionRepository extends Repository<TechnicalCondition, Long> {
    @Query(value = "select * from technical_condition", nativeQuery = true)
    Optional<List<TechnicalCondition>> getTechnicalConditions();
}
