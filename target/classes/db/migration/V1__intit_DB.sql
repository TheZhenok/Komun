create sequence hibernate_sequence start 1 increment 1;

create table user_role (
    user_id int8 not null,
    roles varchar(255)
                       );

create table usr (
    id int8 not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id),
    count_electricity int8,
    count_heating int8,
    count_trash int8,
    count_water_cold int8,
    count_water_hot int8,
    count_water_trash int8
                 );

alter table if exists message
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;