--create database reimbursement
create schema dbr;


drop table if exists dbr.ers_reimbursment_status cascade;
create table dbr.ers_reimbursment_status(
	reimb_status_id int generated always as identity primary key,
	reimb_status varchar(20) not null unique
);

drop table if exists dbr.ers_reimbursment_type cascade;
create table dbr.ers_reimbursment_type(
	reimb_type_id int generated always as identity primary key,
	reimb_type varchar(20) not null unique
);

drop table if exists dbr.ers_reimbursment_roles cascade;
create table dbr.ers_reimbursment_roles(
	ers_user_role_id int generated always as identity primary key,
	ers_role varchar(20) not null unique
);


drop table if exists dbr.ers_users cascade;
create table dbr.ers_users(
	ers_users_id int generated always as identity primary key,
	ers_username varchar(50) not null unique,
	ers_password varchar(100) not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100) not null,
	user_email  varchar(100) not null unique,
	user_role_id int not null references dbr.ers_reimbursment_roles(ers_user_role_id)
);

drop table if exists dbr.ers_reimbursement cascade;
create table dbr.ers_reimbursement(
	reimb_id int generated always as identity primary key,
	reimb_amount decimal,
	reimb_submitted timestamp default localtimestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_author_id int not null references dbr.ers_users(ers_users_id),
	reimb_resolver_id int references dbr.ers_users(ers_users_id),
	reimb_status_id int not null references dbr.ers_reimbursment_status(reimb_status_id),
	reimb_type_id int not null references dbr.ers_reimbursment_type(reimb_type_id)
);

--DEFAULT VALUES

insert into dbr.ers_reimbursment_type (reimb_type) values
('LODGING'),
('TRAVEL'),
('FOOD'),
('OTHER') returning reimb_type_id, reimb_type;

insert into dbr.ers_reimbursment_status (reimb_status) values
('Pending'),
('Approved'),
('Denied') returning reimb_status_id, reimb_status;

insert into dbr.ers_reimbursment_roles (ers_role) values
('Finance Manager'),
('Employee') returning ers_user_role_id, ers_role;

create or replace view dbr.usertable as
	select 
		ers.ers_users_id as id,
		ers.ers_username as username,
		ers.ers_password as password,
		ers.user_first_name as firstname,
		ers.user_last_name as lastname,
		ers.user_email as email,
		r.ers_role as role
	from dbr.ers_users ers
	inner join dbr.ers_reimbursment_roles r
		on r.ers_user_role_id = ers.user_role_id ;
	
create or replace view dbr.reimtable as
		select 
		r.reimb_id as id,
		r.reimb_amount as amount,
		r.reimb_submitted as submitted,
		r.reimb_resolved as resolved,
		r.reimb_description as description,
		eu.ers_username as author,
		eu2.ers_username as resolver,
		ers.reimb_status as status,
		ert.reimb_type as type 
	from dbr.ers_reimbursement r
	inner join dbr.ers_users eu 
		on r.reimb_author_id = eu.ers_users_id 
	inner join dbr.ers_users eu2 
		on r.reimb_resolver_id = eu2.ers_users_id 
	inner join dbr.ers_reimbursment_status ers 
		on r.reimb_status_id = ers.reimb_status_id 
	inner join dbr.ers_reimbursment_type ert
		on r.reimb_type_id = ert.reimb_type_id;