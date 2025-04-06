--changeset artei:1
create table Booking(
    id bigint PRIMARY KEY,
    user_id bigint,
    hotel_id bigint unique ,
    num_of_adults integer,
    num_of_children integer,
    check_in_date timestamp,
    check_out_date timestamp,
    booking_confirm_code varchar(128)
);

create table Hotels(
    id bigint primary key,
    name varchar(256),
    location varchar(256),
    description varchar(512)
);
create table Rooms(
    id bigint primary key,
    room_type varchar(128),
    price numeric(30,2),
    room_photo_url varchar(256),
    room_description varchar(256)
);
create table Users(
    id bigint primary key,
    name varchar(128),
    password varchar(256),
    phone_number varchar(256),
    role varchar(255) check (role in ('USER','ADMIN'))
)
