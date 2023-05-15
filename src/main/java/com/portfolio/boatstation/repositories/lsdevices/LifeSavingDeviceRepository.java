package com.portfolio.boatstation.repositories.lsdevices;

import com.portfolio.boatstation.entities.LifeSavingDevice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional
public interface LifeSavingDeviceRepository extends Repository<LifeSavingDevice, Long> {
    @Query(value = "insert into life_saving_device(life_saving_device_type_id) values (:lsdTypeId)", nativeQuery = true)
    @Modifying
    void createLifeSavingDevice(Long lsdTypeId);
}
