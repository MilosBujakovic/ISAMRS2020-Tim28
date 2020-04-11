
insert into servisklinickihcentara.authority (id,name) values (1,'PATIENT');
insert into servisklinickihcentara.authority (id,name) values (2,'SYSTEM_ADMIN');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number) VALUES ('PATIENT',1,'locdog96@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Slaven', 'Garic', false, '2017-10-01 21:58:58.508','Jevrejska 55','Novi Sad','Srbija','3524243','in1');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date) VALUES ('ADMIN',2,'milosslaven96@gmail.com', '$2a$10$DgZyVrZ4iR1qN8NcnwboKOvI2NYMkJ6aET.VolHI3VWvv0f42CwrS', 'Nikola', 'Nikolic', true, '2019-10-01 21:58:58.508');



insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (1,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (2,2);