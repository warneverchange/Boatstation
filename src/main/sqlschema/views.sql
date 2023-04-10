CREATE OR REPLACE VIEW _rental_log
            (
             id,
             first_name,
             last_name,
             patronymic,
             passport_data,
             phone_number,
             date,
             duration,
             watercraft_number,
             rental_status
                )
AS
SELECT rl.id,
       cd.first_name,
       cd.last_name,
       cd.patronymic,
       cd.passport_data,
       cd.phone_number,
       rl.date,
       rl.duration,
       wl.watercraft_number,
       rs.name
FROM rental_log rl
         INNER JOIN client_data cd on rl.client_data_id = cd.id
         INNER JOIN watercraft_log wl on rl.watercraft_log_id = wl.id
         INNER JOIN rental_status rs on rl.rental_status_id = rs.id;