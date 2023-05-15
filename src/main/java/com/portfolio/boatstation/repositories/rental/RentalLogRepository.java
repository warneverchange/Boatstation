package com.portfolio.boatstation.repositories.rental;

import com.portfolio.boatstation.entities.RentalLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Transactional
public interface RentalLogRepository extends Repository<RentalLog, Long> {
    @Query(value = "update rental_log set rental_status_id = :rentalStatusId where id = :rentalLogId", nativeQuery = true)
    @Modifying
    void updateRentalStatus(Long rentalLogId, Long rentalStatusId);

    @Query(value="select * from rental_log where rental_status_id = :bookedRentalStatusId", nativeQuery = true)
    Optional<List<RentalLog>> getAllRentalLogsByRentalStatusId(Long bookedRentalStatusId);

    @Query(value="insert into rental_log(" +
            "date_from," +
            " date_to," +
            " client_data_id," +
            " watercraft_log_id," +
            " rental_status_id)" +
            " values(" +
            ":#{#rentalLog.dateFrom}," +
            " :#{#rentalLog.dateTo}," +
            " :#{#rentalLog.clientDataId}," +
            " :#{#rentalLog.watercraftLogId}," +
            " :#{#rentalLog.rentalStatusId})", nativeQuery = true)
    @Modifying
    void createNewRentalLog(RentalLog rentalLog);


    @Query(value ="select * from rental_log where id = :rentalLogId", nativeQuery = true)
    @Modifying
    Optional<RentalLog> getRentalLogById(Long rentalLogId);
}
