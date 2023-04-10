create table swimming_danger_type (
    id int unsigned not null primary key auto_increment,
    name varchar(32) not null unique,
    index (name)
);

create table swimming_danger (
    id int unsigned not null primary key auto_increment,
    water_id int unsigned not null,
    watercraft_type_id int unsigned not null ,
    swimming_danger_type_id int unsigned not null ,
    foreign key (water_id) references water(id),
    foreign key (watercraft_type_id) references watercraft_type(id),
    foreign key (swimming_danger_type_id) references swimming_danger_type(id)
);


