package com.portfolio.boatstation.services.watercraft;

import com.portfolio.boatstation.entities.*;
import com.portfolio.boatstation.entities.views.FreeWatercraftView;
import com.portfolio.boatstation.entities.views.WatercraftNumbersView;
import com.portfolio.boatstation.entities.views.WatercraftsView;

import java.util.List;
import java.util.function.Predicate;

public interface WatercraftService {
    List<WatercraftsView> getWatercraftViews();

    void updateWatercraftTechnicalCondition(Long watercraftLogId, Long technicalConditionId);

    void updateWatercraftPosition(Long watercraftLogId, Long waterId);

    void updateWatercraftLogWatercraft(Long watercraftLogId, Long watercraftId);

    List<Model> getModels();

    List<Brand> getBrands();

    List<TechnicalCondition> getTechnicalConditions();

    WatercraftLog saveWatercraftLog(WatercraftLog watercraftLog);

    List<Watercraft> getWatercrafts();

    void deleteWatercraftLog(Long watercraftLogId);

    boolean contains(Predicate<WatercraftLog> condition);

    List<WatercraftNumbersView> getWatercraftNumbers();

    List<FreeWatercraftView> getFreeWatercrafts();
}
