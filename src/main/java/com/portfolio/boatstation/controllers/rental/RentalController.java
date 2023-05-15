package com.portfolio.boatstation.controllers.rental;

import com.portfolio.boatstation.entities.RentalLog;
import com.portfolio.boatstation.entities.views.BookedDatetimesView;
import com.portfolio.boatstation.requests.BookingCreateBody;
import com.portfolio.boatstation.services.rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @GetMapping("/date-times/booked")
    public ResponseEntity<List<BookedDatetimesView>> getBookedDateTimes() {
        return ResponseEntity.ok(rentalService.getBookedDateTimes());
    }

    @PostMapping
    public void createRentalLog(@RequestBody BookingCreateBody bookingCreateBody) {
        rentalService.createRentalLog(bookingCreateBody);
    }
}
