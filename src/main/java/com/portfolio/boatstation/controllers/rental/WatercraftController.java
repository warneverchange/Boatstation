package com.portfolio.boatstation.controllers.rental;

import com.portfolio.boatstation.entities.*;
import com.portfolio.boatstation.entities.views.FreeWatercraftView;
import com.portfolio.boatstation.entities.views.WatercraftNumbersView;
import com.portfolio.boatstation.entities.views.WatercraftsView;
import com.portfolio.boatstation.services.watercraft.WatercraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WatercraftController {
    private final WatercraftService watercraftService;

    @Autowired
    public WatercraftController(WatercraftService watercraftService) {
        this.watercraftService = watercraftService;
    }

    @GetMapping("/watercraft-with-technical-info")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<WatercraftsView>> getWatercraftView() {
        return ResponseEntity.ok(watercraftService.getWatercraftViews());
    }


    @GetMapping("/watercraft/numbers")
    public ResponseEntity<List<WatercraftNumbersView>> getWatercraftNumbersView() {
        return ResponseEntity.ok(watercraftService.getWatercraftNumbers());
    }

    @GetMapping("/watercraft")
    public ResponseEntity<List<Watercraft>> getWatercraft() {
        return ResponseEntity.ok(watercraftService.getWatercrafts());
    }


    @GetMapping("/technical_conditions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<TechnicalCondition>> getTechnicalConditions() {
        return ResponseEntity.ok(watercraftService.getTechnicalConditions());
    }

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels() {
        return ResponseEntity.ok(watercraftService.getModels());
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        return ResponseEntity.ok(watercraftService.getBrands());
    }

    @GetMapping("/watercraft/free")
    public ResponseEntity<List<FreeWatercraftView>> getFreeWatercrafts() {
        return ResponseEntity.ok(watercraftService.getFreeWatercrafts());
    }
}
