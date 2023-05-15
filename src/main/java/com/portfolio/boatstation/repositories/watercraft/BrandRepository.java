package com.portfolio.boatstation.repositories.watercraft;

import com.portfolio.boatstation.entities.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface BrandRepository extends Repository<Brand, Long> {
    @Query(value = "select * from brand", nativeQuery = true)
    Optional<List<Brand>> getBrands();
}
