insert into servisklinickihcentara.authority (id,name) values (1,'PATIENT');
insert into servisklinickihcentara.authority (id,name) values (2,'SYSTEM_ADMIN');
insert into servisklinickihcentara.authority (id,name) values (3,'CLINIC_ADMIN');
insert into servisklinickihcentara.authority (id,name) values (4,'DOCTOR');

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',1,'slavengaric@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Slaven', 'Garic', true, '2017-10-01 21:58:58.508','Jevrejska 55','Novi Sad','Srbija','3524243','in1','b95df7ae-1158-42ce-b0b7-f4277c48237e');
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, address,city,country,phone_number,insurance_number, uuid) VALUES ('PATIENT',2,'locdog96@gmail.com', '$2a$10$1VZ0mgHBP2378x/62R/pn.eNlhdADdsYo3R1Gb4jvJvcWE2RoyZEK', 'Marko', 'Markovic', true, '2019-07-13 11:58:58.508','Gogoljeva 23','Novi Sad','Srbija','5234234','in2', 'b9414b2b-32e2-4cee-9f3a-f2b5e9122fee');
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date) VALUES ('ADMIN',3,'milosslaven96@gmail.com', '$2a$10$DgZyVrZ4iR1qN8NcnwboKOvI2NYMkJ6aET.VolHI3VWvv0f42CwrS', 'Nikola', 'Nikolic', true, '2019-10-01 21:58:58.508');

insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (1,'dermatoloski',3,20);
insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (2,'opsti',9,10);
insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (3,'oftamoloski',4,20);
insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (4,'pedijatrijski-osnovni',5,25);
insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (5,'pedijatrijski-detaljni',5,45);
insert into servisklinickihcentara.type_of_exam(id,name,specialty, duration) values (6,'operacija skidanja mladeza',0,60);


insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (1,0,700,1);
update servisklinickihcentara.type_of_exam set price_item_id = 1 where id = 1;

insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (2,0,100,2);
update servisklinickihcentara.type_of_exam set price_item_id = 2 where id = 2;

insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (3,0,500,3);
update servisklinickihcentara.type_of_exam set price_item_id = 3 where id = 3;

insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (4,0,750,4);
update servisklinickihcentara.type_of_exam set price_item_id = 4 where id = 4;

insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (5,0,1300,5);
update servisklinickihcentara.type_of_exam set price_item_id = 5 where id = 5;

insert into servisklinickihcentara.price_item (id,type,base_price, type_of_exam_id) values (6,1,3300,6);
update servisklinickihcentara.type_of_exam set price_item_id = 6 where id = 6;


insert into servisklinickihcentara.clinic (id, name, address) values(1, 'Tirsova','Beogradska 33');
insert into servisklinickihcentara.clinic (id, name, address) values(2, 'Hirurska','Vojvodjanska 13');
insert into servisklinickihcentara.clinic (id, name, address) values(3, 'Dermatoloska','Safarikova 33');
insert into servisklinickihcentara.clinic (id, name, address) values(4, 'Oftamoloska','Temerinska 67');
insert into servisklinickihcentara.clinic (id, name, address) values(5, 'Opsta','Vojvodjanskih brigada 134');

insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (1,4);
insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (1,5);
insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (3,1);
insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (4,3);
insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (5,2);
insert into servisklinickihcentara.clinic_type_of_exams (clinic_id,type_of_exam_id) values (2,6);

insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',4,'milanmilanovic@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Milan', 'Milanovic', true, '2019-10-01 21:58:58.508',false,1,'09:00:00','17:00:00', 5);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',5,'stevanstevanovicoft@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Stevan', 'Stevanovic', true, '2019-10-01 21:58:58.508',false,2,'09:00:00','17:00:00', 4);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',6,'ankadermatolog@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Anka', 'Ivanovic', true, '2019-10-01 21:58:58.508',false,3,'09:00:00','18:00:00', 3);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',7,'milanhirurg@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Milan', 'Stojanovic', true, '2019-10-01 21:58:58.508',false,2,'09:00:00','18:00:00', 0);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',8,'jovanopstamedicina@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Jovan', 'Jovanovic', true, '2019-10-01 21:58:58.508',false,5,'09:00:00','18:00:00', 9);
insert into servisklinickihcentara.user (dtype,id,email, password, name, surname, enabled, last_password_reset_date, on_vacation, clinic_id, shift_start,shift_end,specialty) VALUES ('DOCTOR',9,'miragavricporodicni@gmail.com', '$2a$10$oRKFEa5ST812Y39m3zWJ2OB3NScFTyvaiRS9NyJt17COs71yh6rkK', 'Mira', 'Gavric', true, '2019-10-01 21:58:58.508',false,5,'08:00:00','16:00:00', 9);

insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (1,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (2,1);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (3,2);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (4,4);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (5,4);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (6,4);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (7,4);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (8,4);
insert  into servisklinickihcentara.user_authority (user_id,authority_id) values (9,4);

insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (1, 'brufen','protiv visoke temperature','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (2, 'paracetamol','glavobolja i kostobolja','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (3, 'strepsils','za grlo','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (4, 'antibiotik','prehlada','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (5, 'aciklovir','protiv herpesa','galenika');
insert into servisklinickihcentara.medication (id, name, description, manufacturer) values (6, 'melem','protiv oziljaka','galenika');

insert into servisklinickihcentara.diagnosis (id, name, medication) values (1, 'Devijacija nosa','hirurska operacija');
insert into servisklinickihcentara.diagnosis (id, name, medication) values (2, 'Mladez-fibron','hirurska operacija');
insert into servisklinickihcentara.diagnosis (id, name, medication) values (3, 'Urastanje nokta','hirurska operacija');
insert into servisklinickihcentara.diagnosis (id, name, medication) values (4, 'Temperatura i prehlada','lijekovi');
insert into servisklinickihcentara.diagnosis (id, name, medication) values (5, 'Upala grla','lijekovi');

insert into servisklinickihcentara.patient_medications (patient_id, medication_id) values (1, 1);
insert into servisklinickihcentara.patient_medications (patient_id, medication_id) values (1, 2);

insert into servisklinickihcentara.room (id, number, clinic_id) values (1, 'oftamoloska',4);
insert into servisklinickihcentara.room (id, number, clinic_id) values (2, 'dermatoloska',3);
insert into servisklinickihcentara.room (id, number, clinic_id) values (3, 'pedijatrijska-opsta',1);
insert into servisklinickihcentara.room (id, number, clinic_id) values (4, 'pedijatrijska-detaljni',1);
insert into servisklinickihcentara.room (id, number, clinic_id) values (5, 'opsta 1',5);
insert into servisklinickihcentara.room (id, number, clinic_id) values (6, 'opsta 2',5);

--za predefinisane oftamoloske termini
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (1, 1, '2020-07-06 14:30:00.000', '2020-07-03 14:50:00.000');
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (2, 1, '2020-07-01 11:10:00.000', '2020-07-01 11:30:00.000');
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (3, 1, '2020-07-02 09:30:00.000', '2020-07-03 09:50:00.000');

--za predefinisane opste termini
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (4, 5, '2020-07-03 14:30:00.000', '2020-07-03 14:40:00.000');
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (5, 5, '2020-07-01 11:10:00.000', '2020-07-01 11:20:00.000');
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (6, 6, '2020-07-02 09:30:00.000', '2020-07-03 09:40:00.000');

insert into servisklinickihcentara.medical_record (id, age,height,weight,diopter,alergies, bloodtype, rhfactor) values (1,23,183,75,'-0.75','Alergija na ambroziju i polen.',1,1);
update servisklinickihcentara.user set medical_record_id = 1 where id = 1;

--predefinisani oftamoloski pregledi
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (1, 1, 4, 5, 3, 0, true, true, false);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (2, 2, 4, 5, 3, 0, true, true, false);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (3, 3, 4, 5, 3, 0, true, true, false);

--predefinisani opsti pregledi
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (4, 4, 5, 8, 2, 0, true, true, false);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (5, 5, 5, 8, 2, 0, true, true, false);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled) values (6, 6, 5, 9, 2, 0, true, true, false);


insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (7, 2, '2013-07-02 09:40:00.000', '2013-07-02 10:00:00.000');
insert into servisklinickihcentara.appointment_report (id, diagnosis_id, description,medical_record_id) values (1,2,'Potrebna je hitna operacija',1);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled,patient_id, report_id) values (7, 7, 3, 6, 1, 0, false, true, false,1,1);

insert into servisklinickihcentara.room (id, number, clinic_id) values (7, 'operaciona',2);
insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (8, 7, '2013-07-12 09:30:00.000', '2013-07-12 10:30:00.000');
insert into servisklinickihcentara.appointment_report (id, diagnosis_id, description,medical_record_id) values (2,2,'Operacija uspjesno obavljena',1);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled,patient_id, report_id) values (8, 8, 2, 7, 6, 1, false, true, false,1,2);
insert into servisklinickihcentara.prescription (id, medication_id, amount_per_day,verified) values (1, 6, 2, true);
insert  into servisklinickihcentara.appointment_report_prescription (appointment_report_id,prescription_id) values (2,1);



insert into servisklinickihcentara.term (id, room_id, start_time,end_time) values (9, 5, '2015-03-02 09:30:00.000', '2013-03-02 09:45:00.000');
insert into servisklinickihcentara.appointment_report (id, diagnosis_id, description,medical_record_id) values (3,4,'Preporuceno je lijecenje lijekovima.',1);
insert into servisklinickihcentara.appointment (id, term_id, clinic_id,employee_id, type_of_exam_id, type, predefined, active, cancelled,patient_id, report_id) values (9, 9, 5, 9, 2, 0, false, true, false,1,3);

insert into servisklinickihcentara.prescription (id, medication_id, amount_per_day,verified) values (2, 1, 2, true);
insert into servisklinickihcentara.prescription (id, medication_id, amount_per_day,verified) values (3, 4, 3, true);


insert  into servisklinickihcentara.appointment_report_prescription (appointment_report_id,prescription_id) values (3,2);
insert  into servisklinickihcentara.appointment_report_prescription (appointment_report_id,prescription_id) values (3,3);


insert into servisklinickihcentara.clinic_rating (id, clinic_id, patient_id, grade) values (1, 3 , 1, 4);
insert into servisklinickihcentara.clinic_rating (id, clinic_id, patient_id, grade) values (2, 2 ,1, 3);
insert into servisklinickihcentara.clinic_rating (id, clinic_id, patient_id, grade) values (3, 5 ,1, 5);

insert into servisklinickihcentara.doctor_rating (id, doctor_id, patient_id, grade) values (1, 6 , 1, 5);
insert into servisklinickihcentara.doctor_rating (id, doctor_id, patient_id, grade) values (2, 7 , 1, 3);
insert into servisklinickihcentara.doctor_rating (id, doctor_id, patient_id, grade) values (3, 9 , 1, 4);

