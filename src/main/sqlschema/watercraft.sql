create table brand
(
    id   int unsigned not null primary key auto_increment,
    name varchar(256) not null unique,
    index (name)
);

create table model
(
    id       int unsigned not null primary key auto_increment,
    name     varchar(256) not null unique,
    brand_id int unsigned not null unique,
    foreign key (brand_id) references brand (id),
    index (name)
);

create table watercraft_type
(
    id   int unsigned not null primary key auto_increment,
    name varchar(32)  not null unique,
    index (name)
);

create table watercraft
(
    id                 int unsigned not null primary key auto_increment,
    issue_year         year         not null,
    model_id           int unsigned not null unique,
    watercraft_type_id int unsigned not null unique,
    foreign key (model_id) references model (id),
    foreign key (watercraft_type_id) references watercraft_type (id),
    index (issue_year)
);

alter table watercraft
    add constraint
        foreign key (model_id) references model (id)
            on update cascade on delete restrict;

alter table watercraft
    add constraint
        foreign key (watercraft_type_id) references watercraft_type (id)
            on update cascade on delete restrict;