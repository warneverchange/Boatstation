create table water_type
(
    id   int unsigned not null primary key auto_increment,
    name varchar(32)  not null unique,
    index (name)
);

create table water
(
    id            int unsigned not null primary key auto_increment,
    name          varchar(45)  not null unique,
    water_type_id int unsigned not null unique,
    foreign key (water_type_id) references water_type (id),
    index (name)
);