package com.portfolio.boatstation.controllers.admin;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.RentalStatus;
import com.portfolio.boatstation.entities.views.RentalTableView;
import com.portfolio.boatstation.services.rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rental")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRentalController {
    private final RentalService rentalService;

    @Autowired
    public AdminRentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping()
    public ResponseEntity<List<RentalTableView>> getRentalData() {
        return new ResponseEntity<>(rentalService.getRentalTableData(), HttpStatus.OK);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<RentalStatus>> getAllRentalStatuses() {
        return new ResponseEntity<>(rentalService.getAllRentalStatuses(), HttpStatus.OK);
    }

    @PostMapping("/{rental_log_id}/rental-status/{rental_status_id}")
    public void updateRentalStatus(
            @PathVariable(name = "rental_log_id") Long rentalLogId,
            @PathVariable(name="rental_status_id") Long rentalStatusId) {
        rentalService.updateRentalStatus(rentalLogId, rentalStatusId);

    }


    @GetMapping("{rentalLogId}/client")
    public ResponseEntity<ClientData> getClientDataByRentalLogId(
            @PathVariable("rentalLogId") Long rentalLogId) {
        return ResponseEntity.ok(rentalService.getClientDataByRentalLogId(rentalLogId));
    }
}
