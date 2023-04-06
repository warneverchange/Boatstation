create table briefing_type
(
    id   int unsigned not null primary key auto_increment,
    name varchar(32)  not null unique,
    index (name)
);

create table briefing_log
(
    id               int unsigned not null primary key auto_increment,
    date             datetime     not null default current_timestamp(),
    briefing_type_id int unsigned not null unique,
    rental_log_id    int unsigned not null unique,
    employee_data_id int unsigned not null unique,
    foreign key (briefing_type_id) references briefing_type (id),
    foreign key (rental_log_id) references rental_log (id),
    foreign key (employee_data_id) references employee_data (id)
);


alter table briefing_log
    add constraint
        foreign key (briefing_type_id) references briefing_log (id)
            on update cascade on delete restrict;


alter table briefing_log
    add constraint
        foreign key (rental_log_id) references rental_log (id)
            on update cascade on delete restrict;


alter table briefing_log
    add constraint
        foreign key (employee_data_id) references employee_data (id)
            on update cascade on delete restrict;

