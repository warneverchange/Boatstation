package com.portfolio.boatstation.repositories.lsdevices;

import com.portfolio.boatstation.entities.LifeSavingDeviceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface LifeSavingDeviceTypeRepository extends Repository<LifeSavingDeviceType, Long> {
    @Query(value = "select * from life_saving_device_type", nativeQuery = true)
    Optional<List<LifeSavingDeviceType>> getLsdTypes();
}
