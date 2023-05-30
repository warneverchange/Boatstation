CREATE OR REPLACE VIEW _rental_log
            (
             id,
             first_name,
             last_name,
             patronymic,
             passport_data,
             phone_number,
             date_from,
             date_to,
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
       rl.date_from,
       rl.date_to,
       wl.watercraft_number,
       rs.name
FROM rental_log rl
         INNER JOIN client_data cd on rl.client_data_id = cd.id
         LEFT JOIN watercraft_log wl on rl.watercraft_log_id = wl.id
         INNER JOIN rental_status rs on rl.rental_status_id = rs.id;


create or replace view free_watercraft as
select watercraft_log.id as id,
       watercraft_number,
       t.id              as water_type_id,
       t.name            as water_type,
       w.id              as water_id,
       w.name            as water,
       wt.id             as watercraft_type_id,
       wt.name           as watercraft_type,
       b.id              as brand_id,
       b.name            as brand,
       m.id              as model_id,
       m.name            as model,
       wc.id             as watercraft_id,
       wc.issue_year     as watercraft_issue_year
from watercraft_log
         inner join technical_condition tc on watercraft_log.technical_condition_id = tc.id
         inner join water w on watercraft_log.water_id = w.id
         inner join water_type t on w.water_type_id = t.id
         inner join watercraft wc on watercraft_log.watercraft_id = wc.id
         inner join model m on wc.model_id = m.id
         inner join brand b on m.brand_id = b.id
         inner join watercraft_type wt on wc.watercraft_type_id = wt.id
where tc.id = 3;

create or replace view watercrafts as
select watercraft_log.id as id,
       t.name            as water_type,
       w.name            as water_name,
       wt.name           as watercraft_type_name,
       b.name            as brand_name,
       m.name            as model_name,
       wc.issue_year     as issue_year,
       watercraft_number,
       tc.name           as technical_condition
from watercraft_log
         inner join technical_condition tc on watercraft_log.technical_condition_id = tc.id
         inner join water w on watercraft_log.water_id = w.id
         inner join water_type t on w.water_type_id = t.id
         inner join watercraft wc on watercraft_log.watercraft_id = wc.id
         inner join model m on wc.model_id = m.id
         inner join brand b on m.brand_id = b.id
         inner join watercraft_type wt on wc.watercraft_type_id = wt.id;


create or replace view watercraft_numbers as
select id, watercraft_number
from watercraft_log;


create or replace view booked_datetimes as
select id, date_from, date_to, watercraft_log_id
from rental_log
where date_from > current_timestamp()
  and watercraft_log_id is not null;


create or replace view _life_saving_devices as
select life_saving_device_log.id                as _id,
       life_saving_device_log.watercraft_log_id as _watercraft_log_id,
       LPAD(lsd.id, 10, '0')                    as id,
       lsdt.name                                as life_saving_device_type
from life_saving_device_log
         inner join life_saving_device lsd on life_saving_device_log.life_saving_device_id = lsd.id
         inner join life_saving_device_type lsdt on lsd.life_saving_device_type_id = lsdt.id;

create or replace view _free_life_saving_devices as
select life_saving_device.id                as _id,
       LPAD(life_saving_device.id, 10, '0') as id,
       lsdt.name
from life_saving_device
         inner join life_saving_device_type lsdt on life_saving_device.life_saving_device_type_id = lsdt.id
where life_saving_device.id not in
      (select life_saving_device_id from life_saving_device_log);


create or replace view employees as
select employee_data.id as id,
       cd.id            as client_data_id,
       ejt.name         as job_title,
       cd.first_name    as first_name,
       cd.last_name     as last_name,
       cd.patronymic    as patronymic,
       cd.phone_number  as phone_number,
       count
from employee_data
         inner join employee_job_title ejt on employee_data.employee_job_title_id = ejt.id
         inner join client_data cd on employee_data.client_data_id = cd.id
         left join (select client_data_id, count(*) as count
                    from briefing_log
                    where date > now() - interval 1 month
                    group by client_data_id) wme on employee_data.id = wme.client_data_id
order by count;

create or replace view briefings as
select briefing_log.id      as id,
       rl.id as rental_log_id,
       briefing_log.date    as date,
       bt.name              as briefing_type,
       ed.first_name        as employee_first_name,
       ed.last_name         as employee_last_name,
       ed.patronymic        as employee_patronymic,
       ed.phone_number      as employee_phone_number,
       cd.first_name        as client_first_name,
       cd.last_name         as client_last_name,
       cd.patronymic        as client_patronymic,
       cd.phone_number      as client_phone_number
from briefing_log
         inner join briefing_type bt on briefing_log.briefing_type_id = bt.id
         inner join client_data ed on briefing_log.client_data_id = ed.id
         inner join rental_log rl on briefing_log.rental_log_id = rl.id
         inner join client_data cd on rl.client_data_id = cd.id;


create or replace view all_life_saving_devices as
select life_saving_device.id                as _id,
       LPAD(life_saving_device.id, 10, '0') as id,
       lsdt.name                            as name
from life_saving_device
         inner join life_saving_device_type lsdt on life_saving_device.life_saving_device_type_id = lsdt.id



create procedure getRentalData()
    begin
        update rental_log set rental_status_id = 2 where now() > date_to;
        select * from _rental_log;
    end;


select water_type.* , brand.* from water_type, brand;
