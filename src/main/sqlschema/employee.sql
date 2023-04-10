create table employee_job_title(
    id int unsigned not null primary key auto_increment,
    name varchar(32) not null unique ,
    index (name)
);

create table employee_data(
    id int unsigned not null primary key auto_increment,
    first_name varchar(256) not null ,
    last_name varchar(256) not null ,
    patronymic varchar(256),
    passport_data varchar(10) not null unique ,
    phone_number varchar(13) not null unique ,
    employee_job_title_id int unsigned not null ,
    foreign key (employee_job_title_id) references employee_job_title(id),
    index (first_name, last_name, patronymic),
    check ( passport_data regexp '[0-9]{10}'),
    check ( phone_number regexp '[0-9]{11,13}')
);