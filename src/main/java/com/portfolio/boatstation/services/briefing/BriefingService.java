package com.portfolio.boatstation.services.briefing;

import com.portfolio.boatstation.entities.BriefingLog;
import com.portfolio.boatstation.entities.BriefingType;
import com.portfolio.boatstation.entities.views.BriefingsView;

import java.util.List;

public interface BriefingService {
    List<BriefingType> getBriefingTypes();

    void createBriefingLog(BriefingLog briefingLog);

    Long getBriefingLogIdByRentalLogId(Long rentalLogId);

    List<BriefingsView> getBriefings();

    List<Long> getReportedBriefings();
}
