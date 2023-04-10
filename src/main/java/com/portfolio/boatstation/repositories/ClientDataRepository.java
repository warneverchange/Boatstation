package com.portfolio.boatstation.repositories;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.views.security.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ClientDataRepository extends Repository<ClientData, Long> {
    @Query(value = "insert into client_data(" +
            "first_name," +
            " last_name," +
            " patronymic," +
            " passport_data," +
            " phone_number)" +
            " values (" +
            ":#{#clientData.firstName}," +
            " :#{#clientData.lastName}," +
            " :#{#clientData.patronymic}," +
            " :#{#clientData.passportData}," +
            " :#{#clientData.phoneNumber})", nativeQuery = true)
    @Modifying
    void saveNewClientData(ClientData clientData);

    @Query(value = "select id from client_data" +
            " where" +
            " first_name = :#{#clientData.firstName} and" +
            " last_name = :#{#clientData.lastName} and" +
            " patronymic = :#{#clientData.patronymic} and " +
            "passport_data = :#{#clientData.passportData} and " +
            "phone_number = :#{#clientData.phoneNumber} ", nativeQuery = true)
    Optional<Long> getIdByClientData(ClientData clientData);
}
