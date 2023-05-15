package com.portfolio.boatstation.repositories.lsdevices;

import com.portfolio.boatstation.entities.LifeSavingDeviceLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional
public interface LifeSavingDeviceLogRepository extends Repository<LifeSavingDeviceLog, Long> {
    @Modifying
    @Query(value = "delete from life_saving_device_log where id = :lifeSavingDeviceLogId", nativeQuery = true)
    void deleteLifeSavingDeviceLog(Long lifeSavingDeviceLogId);

    @Modifying
    @Query(value = "insert into life_saving_device_log(life_saving_device_id, watercraft_log_id) values (:lifeSavingDeviceId, :watercraftLogId)", nativeQuery = true)
    void createLog(Long lifeSavingDeviceId, Long watercraftLogId);
}
