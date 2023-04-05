create table life_saving_device_type(
        id int unsigned not null primary key auto_increment,
        name varchar(32) not null unique ,
        index(name)
);

create table life_saving_device(
    id int unsigned not null primary key auto_increment,
    life_saving_device_type_id int unsigned not null unique ,
    watercraft_log_id int unsigned unique,
    foreign key (life_saving_device_type_id) references life_saving_device_type(id),
    foreign key (watercraft_log_id) references watercraft_log(id)
);