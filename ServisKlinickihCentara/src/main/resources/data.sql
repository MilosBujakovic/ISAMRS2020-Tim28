
insert into servisklinickihcentara.authority (id,name) values (1,'PATIENT');
insert into servisklinickihcentara.authority (id,name) values (2,'SYSTEM_ADMIN');
insert into servisklinickihcentara.authority (id,name) values (3,'CLINIC_ADMIN');
insert into servisklinickihcentara.authority (id,name) values (4,'DOCTOR');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',1,'slavengaric@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Slaven', 'Garic', true, '2017-10-01 21:58:58.508','Jevrejska 55','Novi Sad','Srbija','3524243','in1','b95df7ae-1158-42ce-b0b7-f4277c48237e');
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',2,'locdog96@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Marko', 'Markovic', false, '2019-07-13 11:58:58.508','Gogoljeva 23','Novi Sad','Srbija','5234234','in2', 'b9414b2b-32e2-4cee-9f3a-f2b5e9122fee');
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date) VALUES ('ADMIN',3,'milosslaven96@gmail.com', '$2a$10$DgZyVrZ4iR1qN8NcnwboKOvI2NYMkJ6aET.VolHI3VWvv0f42CwrS', 'Nikola', 'Nikolic', true, '2019-10-01 21:58:58.508');





insert into servisklinickihcentara.clinic (id, name, address, specialty) values(1, 'Tirsova','Beogradska 33',6);
insert into servisklinickihcentara.clinic (id, name, address, specialty) values(2, 'Klinika 1','Vojvodjanska 13',4);
insert into servisklinickihcentara.clinic (id, name, address, specialty) values(3, 'Klinika 2','Safarikova 33',3);
insert into servisklinickihcentara.clinic (id, name, address, specialty) values(4, 'Klinika 3','Temerinska 67',8);

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',4,'milanmilanovic@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Milan', 'Milanovic', true, '2019-10-01 21:58:58.508',false,1,'09:00:00','17:00:00', 6);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',5,'stevanstevanovic@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Stevan', 'Stevanovic', true, '2019-10-01 21:58:58.508',false,2,'09:00:00','17:00:00', 4);





insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (1,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (2,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (3,2);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (4,4);


insert into servisklinickihcentara.medical_record (id, patient_id, age, height, weight, bloodtype, rhfactor) VALUES (1,1, 23, 183.0,75.0, 1,1);
update servisklinickihcentara.user set medical_record_id = 1 where id = 1;

insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (1, 'brufen','protiv visoke temperature','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (2, 'paracetamol','glavobolja i kostobolja','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (3, 'strepsils','za grlo','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (4, 'antibiotik','prehlada','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (5, 'aciklovir','protiv herpesa','galenika');


insert into servisklinickihcentara.patient_medications (patient_id, medication_id) values (1, 1);
insert into servisklinickihcentara.patient_medications (patient_id, medication_id) values (1, 2);


insert into servisklinickihcentara.room (id, number, clinic_id) values (1, 'sala 1',1);
insert into servisklinickihcentara.room (id, number, clinic_id) values (2, 'sala 2',1);
insert into servisklinickihcentara.room (id, number, clinic_id) values (3, 'sala 3',1);
insert into servisklinickihcentara.room (id, number, clinic_id) values (4, 'sala 1',2);
insert into servisklinickihcentara.room (id, number, clinic_id) values (5, 'sala 2',2);
insert into servisklinickihcentara.room (id, number, clinic_id) values (6, 'sala 3',2);


insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (1, 1, '2020-07-03 14:30:00.000', '2020-07-03 14:45:00.000',15,500);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (2, 2, '2020-07-01 11:10:00.000', '2020-07-01 11:25:00.000',15,600);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (3, 3, '2020-07-02 09:30:00.000', '2020-07-03 09:45:00.000',15,550);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (4, 4, '2020-07-03 14:30:00.000', '2020-07-03 14:45:00.000',15,500);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (5, 5, '2020-07-01 11:10:00.000', '2020-07-01 11:25:00.000',15,600);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time, duration, price) values (6, 6, '2020-07-02 09:30:00.000', '2020-07-03 09:45:00.000',15,550);



insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, category, type, predefined, active, cancelled) values (1, 1, 1, 4, 6,1, true, true, false);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, category, type, predefined, active, cancelled) values (2, 2, 1, 4, 6, 1, true, true, false);