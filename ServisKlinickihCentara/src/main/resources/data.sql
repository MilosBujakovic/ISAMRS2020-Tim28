
insert into servisklinickihcentara.user (dtype,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number) VALUES ('PATIENT','slaven@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Slaven', 'Garic', true, '2017-10-01 21:58:58.508','Jevrejska 55','Novi Sad','Srbija','3524243','in1');
insert into servisklinickihcentara.authority (name) values ('PATIENT');

insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (1,1);