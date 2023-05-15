package com.portfolio.boatstation.services.rental;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.RentalLog;
import com.portfolio.boatstation.entities.RentalStatus;
import com.portfolio.boatstation.entities.views.BookedDatetimesView;
import com.portfolio.boatstation.entities.views.RentalTableView;
import com.portfolio.boatstation.exceptions.BookingCreateException;
import com.portfolio.boatstation.exceptions.InternalServerErrorException;
import com.portfolio.boatstation.repositories.client.ClientDataRepository;
import com.portfolio.boatstation.repositories.rental.RentalLogRepository;
import com.portfolio.boatstation.repositories.rental.RentalStatusRepository;
import com.portfolio.boatstation.repositories.security.UserRepository;
import com.portfolio.boatstation.requests.BookingCreateBody;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalLogRepository rentalLogRepository;

    private final RentalStatusRepository rentalStatusRepository;

    private final ClientDataRepository clientDataRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;

    @Autowired
    public RentalServiceImpl(RentalLogRepository rentalLogRepository, RentalStatusRepository rentalStatusRepository,
                             ClientDataRepository clientDataRepository, UserRepository userRepository) {
        this.rentalLogRepository = rentalLogRepository;
        this.rentalStatusRepository = rentalStatusRepository;
        this.clientDataRepository = clientDataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RentalTableView> getRentalTableData() {
        return entityManager.createNativeQuery("call getRentalData()", RentalTableView.class).getResultList();
    }

    @Override
    public void updateRentalStatus(Long rentalLogId, Long rentalStatusId) {
        rentalLogRepository.updateRentalStatus(rentalLogId, rentalStatusId);
    }

    @Override
    public List<RentalStatus> getAllRentalStatuses() {
        Optional<List<RentalStatus>> allRentalStatuses = rentalStatusRepository.getAllRentalStatuses();
        if (allRentalStatuses.isEmpty()) {
            throw new InternalServerErrorException();
        }
        return allRentalStatuses.get();
    }

    @Override
    public List<BookedDatetimesView> getBookedDateTimes() {
        return entityManager.createNativeQuery("select * from booked_datetimes", BookedDatetimesView.class).getResultList();
    }

    @Override
    public RentalLog getRentalLogById(Long rentalLogId) {
        return rentalLogRepository.getRentalLogById(rentalLogId).orElseThrow();
    }

    // 1) Пользователь пытается создать вторую запись на одно и то же время
    // 2) Пользователь пытается с помощью api обойти ограничения
    // (исключить возможность записи на одно и то же время двух пользователей)
    // *3) Проверить что дата окончания больше даты начала.
    // *4) Проверить что дата начала больше текущей даты
    @Override
    public void createRentalLog(BookingCreateBody bookingCreateBody) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long clientDataId = userRepository.getClientDataIdByUsername(username).orElseThrow();
        Long bookedRentalStatusId = rentalStatusRepository
                .getBookedRentalStatusId()
                .orElseThrow();
        if (isBookingDateCorrect(bookingCreateBody, clientDataId)) {
            var rentalLog = RentalLog.builder()
                    .watercraftLogId(bookingCreateBody.getWatercraftLogId())
                    .rentalStatusId(bookedRentalStatusId)
                    .clientDataId(clientDataId)
                    .dateFrom(bookingCreateBody.getDateFrom())
                    .dateTo(bookingCreateBody.getDateTo())
                    .build();
            rentalLogRepository.createNewRentalLog(rentalLog);
        }
    }

    @Override
    public boolean isBookingDateCorrect(BookingCreateBody bookingCreateBody, Long clientDataId) {
        if (bookingCreateBody.getDateTo().before(bookingCreateBody.getDateFrom())) {
            throw new BookingCreateException("Booking date to must be after date from", bookingCreateBody);
        } else if (bookingCreateBody.getDateFrom().before(new Timestamp(System.currentTimeMillis() - 3000000))) {
            System.out.println(new Timestamp(System.currentTimeMillis()));
            System.out.println(bookingCreateBody.getDateFrom().toString());
            System.out.println(bookingCreateBody.getDateTo().toString());
            System.out.println(bookingCreateBody.getWatercraftLogId());
            throw new BookingCreateException("Booking datetime from must be after current datetime", bookingCreateBody);
        }
        List<RentalLog> rentalLogs = rentalLogRepository
                .getAllRentalLogsByRentalStatusId(
                        rentalStatusRepository
                                .getBookedRentalStatusId()
                                .orElseThrow())
                .orElseThrow();
        for (var rentalLog : rentalLogs) {
            var _dateFrom = bookingCreateBody.getDateFrom();
            var _dateTo = bookingCreateBody.getDateTo();
            var dateFrom = rentalLog.getDateFrom();
            var dateTo = rentalLog.getDateTo();
            if ((_dateFrom.after(dateFrom) && _dateFrom.before(dateTo))
                    || (_dateTo.after(dateFrom) && _dateTo.before(dateTo))
                    || (_dateTo.equals(dateTo) && _dateFrom.equals(dateFrom))) {
                if (rentalLog.getClientDataId().equals(clientDataId)) {
                    throw new BookingCreateException("Attempting book data again", bookingCreateBody);
                } else {
                    throw new BookingCreateException("Attempting book data booked by other user", bookingCreateBody);
                }
            }
        }
        return true;
    }

    @Override
    public ClientData getClientDataByRentalLogId(Long rentalLogId) {
        return clientDataRepository.getClientDataByRentalLogId(rentalLogId).orElseThrow();
    }

    @Override
    public RentalStatus getBriefingStatus() {
        return rentalStatusRepository.getBriefingRentalStatus().orElseThrow();
    }

    @Override
    public RentalStatus getActiveRentalStatus() {
        return rentalStatusRepository.getActiveRentalStatus().orElseThrow();
    }

}
