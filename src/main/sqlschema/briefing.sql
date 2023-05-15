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
    briefing_type_id int unsigned not null,
    rental_log_id    int unsigned not null unique,
    client_data_id   int unsigned not null,
    foreign key (briefing_type_id) references briefing_type (id),
    foreign key (rental_log_id) references rental_log (id),
    foreign key (client_data_id) references client_data (id)
);


create table report
(
    id              int unsigned not null primary key auto_increment,
    scan            mediumblob   not null,
    briefing_log_id int unsigned unique,
    foreign key (briefing_log_id) references briefing_log(id) on update cascade on delete set null
);

