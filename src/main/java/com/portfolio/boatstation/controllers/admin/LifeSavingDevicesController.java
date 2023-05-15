package com.portfolio.boatstation.controllers.admin;

import com.portfolio.boatstation.entities.LifeSavingDeviceType;
import com.portfolio.boatstation.entities.views.AllLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.FreeLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.LifeSavingDevicesView;
import com.portfolio.boatstation.services.watercraft.LifeSavingDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/lsdevices")
public class LifeSavingDevicesController {
    private final LifeSavingDevicesService lifeSavingDevicesService;

    @Autowired
    public LifeSavingDevicesController(LifeSavingDevicesService lifeSavingDevicesService) {
        this.lifeSavingDevicesService = lifeSavingDevicesService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<AllLifeSavingDevicesView>> getAllLifeSavingDevicesView() {
        return ResponseEntity.ok(lifeSavingDevicesService.getAllLifeSavingDevices());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<LifeSavingDevicesView>> getLifeSavingDevices() {
        return ResponseEntity.ok(lifeSavingDevicesService.getLifeSavingDevicesViews());
    }

    @GetMapping("/free")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FreeLifeSavingDevicesView>> getFreeLifeSavingDevices() {
        return ResponseEntity.ok(lifeSavingDevicesService.getFreeLifeSavingDevices());
    }

    @GetMapping("/types")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<LifeSavingDeviceType>> getLifeSavingDevicesTypes() {
        return ResponseEntity.ok(lifeSavingDevicesService.getLsdTypes());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/type/{lsdTypeId}")
    public void createLifeSavingDevice(@PathVariable("lsdTypeId") Long lsdTypeId) {
        lifeSavingDevicesService.createLifeSavingDevice(lsdTypeId);
    }

    @PostMapping("/{lifeSavingDeviceLogId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteLifeSavingDeviceLog(
            @PathVariable("lifeSavingDeviceLogId") Long lifeSavingDeviceLogId) {
        lifeSavingDevicesService.deleteLifeSavingDeviceLog(lifeSavingDeviceLogId);
    }

    @PostMapping("/{lifeSavingDeviceId}/watercraft_log_id/{watercraftLogId}")
    public void createLifeSavingDeviceLog(
            @PathVariable("lifeSavingDeviceId") Long lifeSavingDeviceId,
            @PathVariable("watercraftLogId") Long watercraftLogId){
        lifeSavingDevicesService.createLifeSavingDeviceLog(lifeSavingDeviceId, watercraftLogId);
    }
}
