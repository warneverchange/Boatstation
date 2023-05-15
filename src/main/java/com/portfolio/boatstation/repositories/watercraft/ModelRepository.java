package com.portfolio.boatstation.repositories.watercraft;

import com.portfolio.boatstation.entities.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ModelRepository extends Repository<Model, Long> {
    @Query(value = "select * from model", nativeQuery = true)
    Optional<List<Model>> getModels();
}
