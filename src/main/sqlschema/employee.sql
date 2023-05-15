create table employee_job_title(
    id int unsigned not null primary key auto_increment,
    name varchar(32) not null unique ,
    index (name)
);

create table employee_data(
    id int unsigned not null primary key auto_increment,
    client_data_id int unsigned not null,
    employee_job_title_id int unsigned not null,
    foreign key (client_data_id) references client_data(id),
    foreign key (employee_job_title_id) references employee_job_title(id)
);


drop table employee_data;