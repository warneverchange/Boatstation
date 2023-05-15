package com.portfolio.boatstation.services.water;

import com.portfolio.boatstation.entities.Water;
import com.portfolio.boatstation.entities.WaterType;

import java.util.List;

public interface WaterService {
    List<Water> getWaters();

    List<WaterType> getWaterTypes();
}
