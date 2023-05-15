package com.portfolio.boatstation.repositories.briefing;

import com.portfolio.boatstation.entities.BriefingType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface BriefingTypeRepository extends Repository<BriefingType, Long> {
    @Query(value = "select * from briefing_type", nativeQuery = true)
    List<BriefingType> getBriefingTypes();

}
