package com.portfolio.boatstation.services.water;

import com.portfolio.boatstation.entities.Water;
import com.portfolio.boatstation.entities.WaterType;
import com.portfolio.boatstation.repositories.water.WaterRepository;
import com.portfolio.boatstation.repositories.water.WaterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterServiceImpl implements WaterService {
    private final WaterRepository waterRepository;
    private final WaterTypeRepository waterTypeRepository;

    @Autowired
    public WaterServiceImpl(WaterRepository waterRepository, WaterTypeRepository waterTypeRepository) {
        this.waterRepository = waterRepository;
        this.waterTypeRepository = waterTypeRepository;
    }

    @Override
    public List<Water> getWaters() {
        return waterRepository.getWaters().orElseThrow();
    }

    @Override
    public List<WaterType> getWaterTypes() {
        return waterTypeRepository.getWaterTypes().orElseThrow();
    }
}
