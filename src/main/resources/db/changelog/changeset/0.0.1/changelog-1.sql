--liquibase formatted sql

create table sock
(
    id          int primary key generated by default as identity,
    color       varchar(100) not null,
    cotton_part int          not null
);