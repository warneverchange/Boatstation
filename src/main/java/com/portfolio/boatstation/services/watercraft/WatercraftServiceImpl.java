package com.portfolio.boatstation.services.watercraft;

import com.portfolio.boatstation.entities.*;
import com.portfolio.boatstation.entities.views.FreeWatercraftView;
import com.portfolio.boatstation.entities.views.WatercraftNumbersView;
import com.portfolio.boatstation.entities.views.WatercraftsView;
import com.portfolio.boatstation.exceptions.WatercraftAlreadyExistException;
import com.portfolio.boatstation.repositories.watercraft.TechnicalConditionRepository;
import com.portfolio.boatstation.repositories.watercraft.WatercraftRepository;
import com.portfolio.boatstation.repositories.watercraft.BrandRepository;
import com.portfolio.boatstation.repositories.watercraft.ModelRepository;
import com.portfolio.boatstation.repositories.watercraft.WatercraftLogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class WatercraftServiceImpl implements WatercraftService {
    private final WatercraftLogRepository watercraftLogRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final TechnicalConditionRepository technicalConditionRepository;
    private final WatercraftRepository watercraftRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public WatercraftServiceImpl(WatercraftLogRepository watercraftLogRepository, ModelRepository modelRepository, BrandRepository brandRepository, TechnicalConditionRepository technicalConditionRepository, WatercraftRepository watercraftRepository) {
        this.watercraftLogRepository = watercraftLogRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.technicalConditionRepository = technicalConditionRepository;
        this.watercraftRepository = watercraftRepository;
    }


    @Override
    public List<WatercraftsView> getWatercraftViews() {
        return entityManager.createNativeQuery("select * from watercrafts", WatercraftsView.class).getResultList();
    }

    @Override
    public void updateWatercraftTechnicalCondition(Long watercraftLogId, Long technicalConditionId) {
        watercraftLogRepository.updateTechnicalCondition(watercraftLogId, technicalConditionId);
    }

    @Override
    public void updateWatercraftPosition(Long watercraftLogId, Long waterId) {
        watercraftLogRepository.updateWatercraftPosition(watercraftLogId, waterId);
    }

    @Override
    public void updateWatercraftLogWatercraft(Long watercraftLogId, Long watercraftId) {
        watercraftLogRepository.updateWatercraftEntity(watercraftLogId, watercraftId);
    }

    @Override
    public List<Model> getModels() {
        return modelRepository.getModels().orElseThrow();
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.getBrands().orElseThrow();
    }

    @Override
    public List<TechnicalCondition> getTechnicalConditions() {
        return technicalConditionRepository.getTechnicalConditions().orElseThrow();
    }

    @Override
    @Transactional
    public WatercraftLog saveWatercraftLog(WatercraftLog watercraftLog) {
        if (contains((log) -> watercraftLog.getId() != null && watercraftLog.getId().equals(log.getId()))) {
            watercraftLogRepository.updateWatercraftLog(watercraftLog);
        } else if (contains((log) -> log.getWatercraftNumber().equals(watercraftLog.getWatercraftNumber()))) {
            throw new WatercraftAlreadyExistException(watercraftLog ,"Watercraft with this number already exist. Try change existing transport");
        } else {
            watercraftLogRepository.createWatercraftLog(watercraftLog);
            Optional<Long> watercraftId = watercraftLogRepository.getWatercraftLogIdByWatercraft(watercraftLog);
            watercraftLog.setId(watercraftId.orElseThrow());
        }
        return watercraftLog;
    }

    @Override
    public List<Watercraft> getWatercrafts() {
        return watercraftRepository.getWatercrafts().orElseThrow();
    }

    @Override
    public void deleteWatercraftLog(Long watercraftLogId) {
        watercraftLogRepository.deleteWatercraftLogById(watercraftLogId);
    }

    @Override
    public boolean contains(Predicate<WatercraftLog> condition) {
        Optional<List<WatercraftLog>> watercraftLogs = watercraftLogRepository.getWatercraftLogs();

        return watercraftLogs.map(logs -> logs.stream().anyMatch(condition)).orElse(false);
    }

    @Override
    public List<WatercraftNumbersView> getWatercraftNumbers() {
        return entityManager.createNativeQuery("select * from watercraft_numbers", WatercraftNumbersView.class).getResultList();
    }

    @Override
    public List<FreeWatercraftView> getFreeWatercrafts() {
        return entityManager.createNativeQuery("select  * from free_watercraft", FreeWatercraftView.class).getResultList();
    }
}
