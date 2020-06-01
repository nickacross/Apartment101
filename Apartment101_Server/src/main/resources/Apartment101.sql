drop table vgp_user cascade constraints;
drop table vgp_apartment cascade constraints;
drop table vgp_application cascade constraints;

DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 10 INCREMENT BY 1;

-- User
-- user_type ADMIN & CUSTOMER 
CREATE TABLE vgp_user(
	username varchar2(50) not null,
	password varchar2(100) not null,
	email varchar2(50) not null,
	user_type varchar2(50) not null, 
	constraint vgp_user_pk primary key (email)
);

-- Apartment
-- apt_type 1B1Bath, 2B1Bath, 2B2Bath
-- availability 0 = false, 1 = true
-- type_of_flooring Laminate, Carpet, Wood, Tile, Linoleum
CREATE TABLE vgp_apartment (
	apt_type varchar2(50) not null,
	no_of_rooms number(2) not null,
	no_of_baths number(2) not null,
	apt_no number(4) not null,
	apt_level number(2) not null,
	type_of_flooring varchar2(50) not null,
	availability number(1) not null,
	constraint vgp_apartment_pk primary key (apt_no)
);

-- Application
-- status 0 = false, 1 = true
CREATE TABLE vgp_application (
	app_id number(10) not null,
	apt_no number(4) not null,
	status number(1) not null,
	user_email varchar2(50) not null, 
	constraint vgp_application_pk primary key (app_id),
	constraint vgp_application_fk1 foreign key (apt_no) references vgp_apartment(apt_no),
	constraint vgp_application_fk2 foreign key (user_email) references vgp_user(email)
);

-- Test@1
insert into vgp_user (username, password, email, user_type) values ('test1', '6778c616b1912689fc368f21d1a4eabb97931cea2ee3ec379850797da778acf9', 'test1@gmail.com', 'ADMIN');
-- Test@2
insert into vgp_user (username, password, email, user_type) values ('test2', '0c4e6700d91615d0c955abe0f9971c4eef6b6c1b4679f085d6bd8a5c6a7f6a87', 'test2@gmail.com', 'ADMIN');


-- Test@3
insert into vgp_user (username, password, email, user_type) values ('test3', 'Test@3', 'test3@gmail.com', 'CUSTOMER');
-- Test@4
insert into vgp_user (username, password, email, user_type) values ('test4', 'Test@4', 'test4@gmail.com', 'CUSTOMER');
-- Test@5
insert into vgp_user (username, password, email, user_type) values ('test5', 'Test@5', 'test5@gmail.com', 'CUSTOMER');

insert into vgp_apartment (apt_type, no_of_rooms, no_of_baths, apt_no, apt_level, type_of_flooring, availability) values ('1B1Bath', 1, 1, 1, 1, 'Linoleum', 1);
insert into vgp_apartment (apt_type, no_of_rooms, no_of_baths, apt_no, apt_level, type_of_flooring, availability) values ('2B2Bath', 2, 2, 2, 1, 'Carpet', 1);

insert into vgp_application (app_id, apt_no, status, user_email) values (3, 1, 1, 'test3@gmail.com');
insert into vgp_application (app_id, apt_no, status, user_email) values (4, 2, 0, 'test3@gmail.com');
insert into vgp_application (app_id, apt_no, status, user_email) values (5, 1, 0, 'test4@gmail.com');
insert into vgp_application (app_id, apt_no, status, user_email) values (6, 2, 0, 'test5@gmail.com');

--select * from VGP_APARTMENT;
--select * from vgp_application;
--select * from vgp_user;
