create table client_data(
    id int unsigned not null primary key auto_increment,
    first_name varchar(256) not null ,
    last_name varchar(256) not null ,
    patronymic varchar(256),
    passport_data varchar(10) not null unique ,
    phone_number varchar(13) not null unique ,
    index (first_name, last_name, patronymic),
    check ( passport_data regexp '[0-9]{10}'),
    check ( phone_number regexp '[0-9]{11,13}')
);


