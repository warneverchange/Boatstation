package com.portfolio.boatstation.repositories.watercraft;

import com.portfolio.boatstation.entities.WatercraftLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@org.springframework.stereotype.Repository
public interface WatercraftLogRepository extends Repository<WatercraftLog, Long> {
    @Query(value = "update watercraft_log set technical_condition_id = :technicalConditionId where id = :watercraftLogId", nativeQuery = true)
    @Modifying
    void updateTechnicalCondition(Long watercraftLogId, Long technicalConditionId);

    @Query(value = "update watercraft_log set water_id = :waterId where id = :watercraftLogId", nativeQuery = true)
    @Modifying
    void updateWatercraftPosition(Long watercraftLogId, Long waterId);

    @Query(value="update watercraft_log set watercraft_id=:watercraftId where id = :watercraftLogId", nativeQuery = true)
    @Modifying
    void updateWatercraftEntity(Long watercraftLogId, Long watercraftId);

    @Query(value = "insert into watercraft_log(watercraft_number, water_id, technical_condition_id, watercraft_id) values (:#{#watercraftLog.watercraftNumber}, :#{#watercraftLog.waterId}, :#{#watercraftLog.technicalConditionId}, :#{#watercraftLog.watercraftId})", nativeQuery = true)
    @Modifying
    void createWatercraftLog(WatercraftLog watercraftLog);

    @Query(value = "select * from watercraft_log", nativeQuery = true)
    Optional<List<WatercraftLog>> getWatercraftLogs();

    @Query(value = "select id from watercraft_log where watercraft_number = :#{#watercraftLog.watercraftNumber} and water_id = :#{#watercraftLog.waterId} and technical_condition_id = :#{#watercraftLog.technicalConditionId} and watercraft_id = :#{#watercraftLog.watercraftId}", nativeQuery = true)
    Optional<Long> getWatercraftLogIdByWatercraft(WatercraftLog watercraftLog);

    @Query(value = "delete from watercraft_log where id = :watercraftLogId", nativeQuery = true)
    @Modifying
    void deleteWatercraftLogById(Long watercraftLogId);

    @Query(value = "update watercraft_log set watercraft_number=:#{#watercraftLog.watercraftNumber}, water_id=:#{#watercraftLog.waterId}, technical_condition_id=:#{#watercraftLog.technicalConditionId}, watercraft_id=:#{#watercraftLog.watercraftId} where id = :#{#watercraftLog.id}", nativeQuery = true)
    @Modifying
    void updateWatercraftLog(WatercraftLog watercraftLog);
}
