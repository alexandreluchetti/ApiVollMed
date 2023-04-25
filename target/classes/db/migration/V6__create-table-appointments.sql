create table appointments(

    id bigint not null auto_increment,
    id_doctor bigint not null,
    id_patient bigint not null,
    datahora datetime not null,

    primary key(id),
    constraint fk_appointment_doctor foreign key(id_doctor) references doctors(id),
    constraint fk_appointment_patient foreign key(id_patient) references patients(id)

);