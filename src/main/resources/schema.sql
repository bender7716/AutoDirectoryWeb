drop table if exists auto cascade;
create table auto
(
    car_trailer         boolean,
    manufacturing_year  integer,
    id                  bigserial not null,
    brand               varchar(255),
    category            varchar(255),
    model               varchar(255),
    registration_number varchar(255),
    type                varchar(255),
    primary key (id)
);