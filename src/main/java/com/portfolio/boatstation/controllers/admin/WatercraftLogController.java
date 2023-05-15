package com.portfolio.boatstation.controllers.admin;

import com.portfolio.boatstation.entities.WatercraftLog;
import com.portfolio.boatstation.services.watercraft.WatercraftService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/watercraft_log")
@PreAuthorize("hasAuthority('ADMIN')")
public class WatercraftLogController {
    private final WatercraftService watercraftService;

    public WatercraftLogController(WatercraftService watercraftService) {
        this.watercraftService = watercraftService;
    }

    @PostMapping("{watercraft_log_id}/technical_condition/{technical_condition_id}")
    public void updateWatercraftTechnicalCondition(
            @PathVariable(name = "watercraft_log_id") Long watercraftLogId,
            @PathVariable(name = "technical_condition_id") Long technicalConditionId) {
        watercraftService.updateWatercraftTechnicalCondition(watercraftLogId, technicalConditionId);
    }

    @DeleteMapping("{watercraftLogId}")
    public void deleteWatercraftLog(@PathVariable("watercraftLogId") Long watercraftLogId) {
        watercraftService.deleteWatercraftLog(watercraftLogId);
    }

    @PostMapping("{watercraft_log_id}/water/{water_id}")
    public void updateWatercraftPosition(
            @PathVariable(name= "watercraft_log_id") Long watercraftLogId,
            @PathVariable(name="water_id") Long waterId) {
        watercraftService.updateWatercraftPosition(watercraftLogId, waterId);
    }

    @PostMapping("{watercraft_log_id}/watercraft-entity/{watercraft_id}")
    public void updateWatercraftEntity(
            @PathVariable(name="watercraft_log_id") Long watercraftLogId,
            @PathVariable(name="watercraft_id") Long watercraftId) {
        watercraftService.updateWatercraftLogWatercraft(watercraftLogId, watercraftId);
    }

    @PostMapping("")
    public ResponseEntity<WatercraftLog> saveWatercraftLog(@RequestBody WatercraftLog watercraftLog) {
        return ResponseEntity.ok(watercraftService.saveWatercraftLog(watercraftLog));
    }
}
