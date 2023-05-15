package com.portfolio.boatstation.controllers.rental;

import com.portfolio.boatstation.entities.Water;
import com.portfolio.boatstation.entities.WaterType;
import com.portfolio.boatstation.services.water.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WaterController {
    private final WaterService waterService;

    @Autowired
    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }

    @GetMapping("/waters")
    public List<Water> getWaters() {
        return waterService.getWaters();
    }

    @GetMapping("/water_types")
    public List<WaterType> getWaterTypes() {
        return waterService.getWaterTypes();
    }
}
