
insert into servisklinickihcentara.authority (id,name) values (1,'PATIENT');
insert into servisklinickihcentara.authority (id,name) values (2,'SYSTEM_ADMIN');
--insert into servisklinickihcentara.authority (id,name) values (3,'CLINIC_ADMIN');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',1,'slavengaric@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Slaven', 'Garic', true, '2017-10-01 21:58:58.508','Jevrejska 55','Novi Sad','Srbija','3524243','in1','b95df7ae-1158-42ce-b0b7-f4277c48237e');
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',2,'locdog96@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Marko', 'Markovic', false, '2019-07-13 11:58:58.508','Gogoljeva 23','Novi Sad','Srbija','5234234','in2', 'b9414b2b-32e2-4cee-9f3a-f2b5e9122fee');


insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date) VALUES ('ADMIN',3,'milosslaven96@gmail.com', '$2a$10$DgZyVrZ4iR1qN8NcnwboKOvI2NYMkJ6aET.VolHI3VWvv0f42CwrS', 'Nikola', 'Nikolic', true, '2019-10-01 21:58:58.508');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, clinic) VALUES ('CLINICADMIN',4,'milosslaven962@gmail.com', '$2a$10$DgZyVrZ4iR1qN8NcnwboKOvI2NYMkJ6aET.VolHI3VWvv0f42CwrS', 'Nikola2', 'Nikolic2', true, '2019-10-01 21:58:58.508', 'Klinika 1');




insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (1,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (2,1);

insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (3,2);

insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (4,2);

