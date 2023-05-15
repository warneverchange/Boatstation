package com.portfolio.boatstation.services.briefing;

import com.portfolio.boatstation.entities.BriefingLog;
import com.portfolio.boatstation.entities.BriefingType;
import com.portfolio.boatstation.entities.RentalStatus;
import com.portfolio.boatstation.entities.views.BriefingsView;
import com.portfolio.boatstation.repositories.briefing.BriefingLogRepository;
import com.portfolio.boatstation.repositories.briefing.BriefingTypeRepository;
import com.portfolio.boatstation.services.rental.RentalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BriefingServiceImpl implements BriefingService{
    private final BriefingTypeRepository briefingTypeRepository;
    private final BriefingLogRepository briefingLogRepository;

    private final RentalService rentalService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BriefingServiceImpl(BriefingTypeRepository briefingTypeRepository, BriefingLogRepository briefingLogRepository, RentalService rentalService) {
        this.briefingTypeRepository = briefingTypeRepository;
        this.briefingLogRepository = briefingLogRepository;
        this.rentalService = rentalService;
    }

    @Override
    public List<BriefingType> getBriefingTypes() {
        return briefingTypeRepository.getBriefingTypes();
    }

    @Override
    public void createBriefingLog(BriefingLog briefingLog) {
        briefingLog.setDate(new Timestamp(System.currentTimeMillis()));
        RentalStatus rentalStatus = rentalService.getBriefingStatus();
        rentalService.updateRentalStatus(briefingLog.getRentalLogId(), rentalStatus.getId());
        briefingLogRepository.save(briefingLog);
    }

    @Override
    public Long getBriefingLogIdByRentalLogId(Long rentalLogId) {
        return briefingLogRepository.getBriefingLogIdByRentalLogId(rentalLogId).orElseThrow();
    }

    @Override
    public List<BriefingsView> getBriefings() {
        return entityManager.createNativeQuery("select * from briefings", BriefingsView.class).getResultList();
    }

    @Override
    public List<Long> getReportedBriefings() {
        return briefingLogRepository.getReportedBriefings();
    }
}
