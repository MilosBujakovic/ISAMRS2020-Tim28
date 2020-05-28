package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.medicalRecordsDTO.DiseaseHistoryDTO;
import com.ServisKlinickihCentara.dto.medicalRecordsDTO.MedicalRecordDTO;
import com.ServisKlinickihCentara.dto.medicalRecordsDTO.PrescriptionMrDTO;
import com.ServisKlinickihCentara.model.employees.Prescription;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.patients.AppointmentReport;
import com.ServisKlinickihCentara.model.patients.MedicalRecord;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private UserService userService;


    public MedicalRecordDTO getMedicalRecord(String patientsEmail) {
        Patient patient = (Patient) userService.findByUsername(patientsEmail);
        MedicalRecord medicalRecord = patient.getMedicalRecord();
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        if (medicalRecord != null) {
            medicalRecordDTO = new MedicalRecordDTO(medicalRecord);
            for (AppointmentReport ar : medicalRecord.getReports()) {
                /*if (ar.getAppointment().getType().equals(AppointmentType.OPERATION)) {
                   continue;
                }*/
                LocalDateTime localDateTime = ar.getAppointment().getTerm().getStartTime().toLocalDateTime();
                LocalDate localDate = localDateTime.toLocalDate();
                String date = localDate.toString();
                DiseaseHistoryDTO diseaseHistoryDTO = new DiseaseHistoryDTO(date,
                            ar.getDiagnosis().getName(), ar.getDiagnosis().getMedication(), ar.getDescription());

                for(Prescription prescription: ar.getPrescriptions()){
                    PrescriptionMrDTO prescriptionMrDTO = new PrescriptionMrDTO(prescription.getMedication().getName(),
                            String.valueOf(prescription.getAmountPerDay()));
                    diseaseHistoryDTO.getPrescriptionMrDTOS().add(prescriptionMrDTO);
                    System.out.println(prescriptionMrDTO);
                }

                medicalRecordDTO.getDiseaseHistoryDTOS().add(diseaseHistoryDTO);
                System.out.println(diseaseHistoryDTO);
            }
            }

        return medicalRecordDTO;

    }

}