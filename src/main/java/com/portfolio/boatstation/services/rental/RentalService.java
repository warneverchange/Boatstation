package com.portfolio.boatstation.services.rental;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.RentalLog;
import com.portfolio.boatstation.entities.RentalStatus;
import com.portfolio.boatstation.entities.views.BookedDatetimesView;
import com.portfolio.boatstation.entities.views.RentalTableView;
import com.portfolio.boatstation.requests.BookingCreateBody;

import java.util.List;

public interface RentalService {

    List<RentalTableView> getRentalTableData();
    void updateRentalStatus(Long rentalLogId, Long rentalStatusId);

    List<RentalStatus> getAllRentalStatuses();

    List<BookedDatetimesView> getBookedDateTimes();

    RentalLog getRentalLogById(Long rentalLogId);

    void createRentalLog(BookingCreateBody bookingCreateBody);

    boolean isBookingDateCorrect(BookingCreateBody bookingCreateBody, Long userId);

    ClientData getClientDataByRentalLogId(Long rentalLogId);

    RentalStatus getBriefingStatus();

    RentalStatus getActiveRentalStatus();
}
