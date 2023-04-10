create table _user(
    id int unsigned not null primary key auto_increment,
    username varchar(256) unique not null,
    password varchar(256) not null ,
    email varchar(256) unique not null ,
    _user_role_id int unsigned not null ,
    client_data_id int unsigned not null unique,
    foreign key (client_data_id) references client_data(id),
    foreign key (_user_role_id) references _user_role(id)
);

create table _user_role(
    id int unsigned not null primary key auto_increment,
    name varchar(256) unique not null
);