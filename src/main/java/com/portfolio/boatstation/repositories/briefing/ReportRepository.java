package com.portfolio.boatstation.repositories.briefing;

import com.portfolio.boatstation.entities.Report;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@org.springframework.stereotype.Repository
@Transactional
public interface ReportRepository extends Repository<Report, Long> {
    @Query(value = "insert into report(scan, briefing_log_id) values(:data, :rentalLogId)", nativeQuery = true)
    @Modifying
    void uploadReport(Long rentalLogId, byte[] data);


    @Query(value = "select * from report where briefing_log_id = :briefingLogId", nativeQuery = true)
    Optional<Report> getReportByBriefingLogId(Long briefingLogId);
}
