package com.portfolio.boatstation.repositories.rental;

import com.portfolio.boatstation.entities.RentalStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RentalStatusRepository extends Repository<RentalStatus, Long> {
    @Query(value = "select * from rental_status", nativeQuery = true)
    Optional<List<RentalStatus>> getAllRentalStatuses();

    @Query(value="select * from rental_status where id = 1", nativeQuery = true)
    Optional<Long> getBookedRentalStatusId();

    @Query(value = "select * from rental_status where id = 4", nativeQuery = true)
    Optional<RentalStatus> getBriefingRentalStatus();

    @Query(value = "select * from rental_status where id = 3", nativeQuery = true)
    Optional<RentalStatus> getActiveRentalStatus();
}
