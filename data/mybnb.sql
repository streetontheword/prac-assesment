drop database if exists mybnb;

create database mybnb; 

use mybnb;


create table acc_occupancy(
    acc_id varchar(10) not null, 
    vacancy int not null,
    primary key(acc_id)

);

create table reservations(
    resv_id varchar(8) not null, 
    name varchar(128),
    email varchar(128),
    arrival_date Date, 
    duration int,
    acc_id varchar(10) not null, 
    
    primary key(resv_id),
    constraint fk_acc_id foreign key(acc_id) references acc_occupancy(acc_id)
    
)