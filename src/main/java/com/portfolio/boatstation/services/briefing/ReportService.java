package com.portfolio.boatstation.services.briefing;

import com.portfolio.boatstation.entities.Report;

public interface ReportService {
    void uploadReport(Long briefingLogId, byte[] data);

    Report getReportScanByBriefingLogId(Long briefingLogId);
}
