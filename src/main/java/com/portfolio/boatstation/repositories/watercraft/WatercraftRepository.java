package com.portfolio.boatstation.repositories.watercraft;

import com.portfolio.boatstation.entities.Watercraft;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface WatercraftRepository extends Repository<Watercraft, Long> {
    @Query(value = "select * from watercraft", nativeQuery = true)
    Optional<List<Watercraft>> getWatercrafts();
}
