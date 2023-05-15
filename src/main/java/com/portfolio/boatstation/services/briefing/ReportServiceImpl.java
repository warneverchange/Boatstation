package com.portfolio.boatstation.services.briefing;

import com.portfolio.boatstation.entities.BriefingLog;
import com.portfolio.boatstation.entities.RentalStatus;
import com.portfolio.boatstation.entities.Report;
import com.portfolio.boatstation.repositories.briefing.BriefingLogRepository;
import com.portfolio.boatstation.repositories.briefing.ReportRepository;
import com.portfolio.boatstation.services.rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final BriefingLogRepository briefingLogRepository;

    private final RentalService rentalService;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, BriefingLogRepository briefingLogRepository, RentalService rentalService) {
        this.reportRepository = reportRepository;
        this.briefingLogRepository = briefingLogRepository;
        this.rentalService = rentalService;
    }

    @Override
    public void uploadReport(Long briefingLogId, byte[] data) {
        Optional<BriefingLog> briefingLog = briefingLogRepository.getBriefingLogById(briefingLogId);
        RentalStatus rentalStatus = rentalService.getActiveRentalStatus();
        rentalService.updateRentalStatus(briefingLog.get().getRentalLogId(), rentalStatus.getId());
        reportRepository.uploadReport(briefingLogId, data);
    }

    @Override
    public Report getReportScanByBriefingLogId(Long briefingLogId) {
        return reportRepository.getReportByBriefingLogId(briefingLogId).orElseThrow();
    }
}
