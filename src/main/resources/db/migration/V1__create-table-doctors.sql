create table doctors(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    speciality varchar(100) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zipcode varchar(9) not null,
    city varchar(100) not null,
    state char(2) not null,
    number varchar(20),
    complement varchar(100),

    primary key(id)

);