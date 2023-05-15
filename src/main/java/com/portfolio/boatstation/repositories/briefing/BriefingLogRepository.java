package com.portfolio.boatstation.repositories.briefing;

import com.portfolio.boatstation.entities.BriefingLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Transactional
public interface BriefingLogRepository extends Repository<BriefingLog, Long> {
    @Modifying()
    @Query(value = "insert into briefing_log(date, briefing_type_id, rental_log_id, client_data_id) values (:#{#briefingLog.date}, :#{#briefingLog.briefingTypeId}, :#{#briefingLog.rentalLogId}, :#{#briefingLog.clientDataId})", nativeQuery = true)
    void save(BriefingLog briefingLog);

    @Query(value = "select id from briefing_log where rental_log_id = :rentalLogId", nativeQuery = true)
    Optional<Long> getBriefingLogIdByRentalLogId(Long rentalLogId);


    @Query(value = "select * from briefing_log where id = :briefingLogId", nativeQuery = true)
    Optional<BriefingLog> getBriefingLogById(Long briefingLogId);

    @Query(value = "select distinct briefing_log_id from report", nativeQuery = true)
    List<Long> getReportedBriefings();
}
