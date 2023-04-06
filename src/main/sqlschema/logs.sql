create table technical_condition
(
    id   int unsigned not null primary key auto_increment,
    name varchar(32)  not null unique,
    index (name)
);

create table watercraft_log
(
    id                     int unsigned not null primary key auto_increment,
    watercraft_number      varchar(11)  not null unique,
    water_id               int unsigned unique,
    technical_condition_id int unsigned not null unique,
    watercraft_id          int unsigned not null unique,
    foreign key (water_id) references water (id),
    foreign key (technical_condition_id) references technical_condition (id),
    foreign key (watercraft_id) references watercraft (id),
    check (substr(watercraft_number, 1, 2)
               regexp '[А-Я]{2}' and substr(watercraft_number, 3, 4)
               regexp '[0-9]{4}' and substr(watercraft_number, 7, 3)
               regexp '[А-Я]{3}' and substr(watercraft_number, 10, 2)
               regexp '[0-9]{2}')
);


create table rental_log
(
    id                int unsigned not null primary key auto_increment,
    date              datetime     not null default current_timestamp(),
    duration          time         not null,
    client_data_id    int unsigned not null unique,
    watercraft_log_id int unsigned not null unique,
    foreign key (client_data_id) references client_data (id),
    foreign key (watercraft_log_id) references watercraft_log (id)
);


alter table watercraft_log
    add constraint
        foreign key (water_id) references water (id)
            on update cascade on delete restrict;
alter table watercraft_log
    add constraint
        foreign key (technical_condition_id) references technical_condition (id)
            on update cascade on delete restrict;
alter table watercraft_log
    add constraint
        foreign key (watercraft_id) references watercraft (id)
            on update cascade on delete restrict;


alter table rental_log
    add constraint
        foreign key (client_data_id) references client_data (id)
            on update cascade on delete restrict;

alter table rental_log
    add constraint
        foreign key (watercraft_log_id) references watercraft_log (id)
            on update cascade on delete restrict;


