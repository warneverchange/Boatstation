package com.portfolio.boatstation.repositories.client;

import com.portfolio.boatstation.entities.ClientData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.View;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Transactional
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


    @Query(value = "select * from client_data where id = :clientDataId", nativeQuery = true)
    ClientData getClientDataById(Long clientDataId);

    @Modifying
    @Query(value = "update client_data set " +
            "first_name = :#{#updatedClientData.getFirstName()}," +
            " last_name = :#{#updatedClientData.getLastName()}," +
            "  patronymic = :#{#updatedClientData.getPatronymic()}," +
            " passport_data = :#{#updatedClientData.getPassportData() }," +
            " phone_number = :#{#updatedClientData.getPhoneNumber()}" +
            " where id = :#{#updatedClientData.getId()}", nativeQuery = true)
    void updateClientData(ClientData updatedClientData);

    @Modifying
    @Query(value = "insert into employee_data(client_data_id, employee_job_title_id) values (:clientDataId, :employeeJobTitleId)", nativeQuery = true)
    void markAsEmployee(Long clientDataId, Long employeeJobTitleId);

    @Query(value = "select " + "client_data_id as id," +
            "cd.first_name as first_name," +
            " cd.last_name as last_name," +
            " cd.patronymic as patronymic," +
            " cd.phone_number as phone_number," +
            " cd.passport_data as passport_data " +
            "from rental_log inner join client_data cd on rental_log.client_data_id = cd.id" +
            " where rental_log.id = :rentalLogId", nativeQuery = true)
    Optional<ClientData> getClientDataByRentalLogId(Long rentalLogId);

    @Query(value = "select * from client_data", nativeQuery = true)
    Optional<List<ClientData>> getAllClients();

    @Modifying
    @Query(value = "delete from employee_data where client_data_id = :employeeId", nativeQuery = true)
    void fireEmployee(Long employeeId);
}
