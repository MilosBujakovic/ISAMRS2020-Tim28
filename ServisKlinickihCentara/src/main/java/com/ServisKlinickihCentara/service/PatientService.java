package com.ServisKlinickihCentara.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.dto.medicalRecordsDTO.DiseaseHistoryDTO;
import com.ServisKlinickihCentara.dto.medicalRecordsDTO.PrescriptionMrDTO;
import com.ServisKlinickihCentara.dto.patientsDTO.PatientFilterViewDTO;
import com.ServisKlinickihCentara.dto.patientsDTO.PatientRecordViewDTO;
import com.ServisKlinickihCentara.model.employees.Prescription;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.model.patients.AppointmentReport;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.repository.AppointmentRepository;
import com.ServisKlinickihCentara.repository.PatientRepository;

@Service
public class PatientService 
{

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;

	public List<PatientFilterViewDTO> filterPatients(String clinicId, String name, String surname, String insuranceNumber,
			String city) {
		long clinic = Long.parseLong(clinicId);
		ArrayList<Appointment> appointments = appointmentRepository.findAll();
		System.out.println("Found all appointments!");
		int i = 0, j;
		do
		{
			if(appointments.get(i).getClinic().getId()!=clinic)
			{
				appointments.remove(i);
			}
			else i++;
		}while(i<appointments.size());
		j = appointments.size();
		i=0;
		do
		{
			if(appointments.get(i).getPatient()==null)
			{
				appointments.remove(i);
			}
			else i++;
		}while(i<appointments.size());
		
		System.out.println("Appointments Filtered! Appointments of clinic with patients: " + appointments.size()+ " out of "+ j);	
        List<Patient> patients = patientRepository.findAll();
        
        System.out.println("all patients delivered!");
        int k = patients.size();
        i=0;
        for(i = 0; i< patients.size(); i++)
        {
        	for(j = 0; j<appointments.size(); j++)
        	{
        			if(patients.get(i).getId()==appointments.get(j).getPatient().getId())
        			{
        				break;
        			}
        			else if(j==appointments.size()-1)
        			{
        				patients.remove(i);
        				i--;
        			}
        	}
        }
        System.out.println("patients of clinic filtered: "+ patients.size() +" out of "+ k);
        if(name!=null && name!="")
        {
        	for(j=0; j< patients.size();j++)
        	{
        		if(!patients.get(j).getName().contains(name))
        			{
        				System.out.println("patient:" + patients.get(j).getInsuranceNumber() + "doesn't match criteria!");
        				patients.remove(j);
        				j--;
        			}
        	}
        }
        if(surname!=null && surname!="")
        {
        	for(j=0; j< patients.size();j++)
        	{
        		if(!patients.get(j).getSurname().contains(surname))
        		{
        			System.out.println("patient:" + patients.get(j).getInsuranceNumber() + "doesn't match criteria!");
        			patients.remove(j);
    				j--;
        		}
        	}
        }
        if(insuranceNumber!=null && insuranceNumber!="")
        {
        	for(j=0; j< patients.size();j++)
        	{
        		if(!patients.get(j).getInsuranceNumber().contains(insuranceNumber))
        		{
        			System.out.println("patient:" + patients.get(j).getInsuranceNumber() + "doesn't match criteria!");
        			patients.remove(j);
    				j--;
        		}
        	}
        }
        if(city!=null && city!="")
        {
        	for(j=0; j< patients.size();j++)
        	{
        		if(!patients.get(j).getCity().contains(city))
        		{
        			System.out.println("patient:" + patients.get(j).getInsuranceNumber() + "doesn't match criteria!");
        			patients.remove(j);
    				j--;
        		}
        	}
        }
        List<PatientFilterViewDTO> patientsDTO = new ArrayList<PatientFilterViewDTO>();
        PatientFilterViewDTO patient;
        System.out.println("Search results:");
        for(j = 0; j<patients.size();j++)
        {
        	patient = new PatientFilterViewDTO(patients.get(j), clinic);
        	System.out.println(patient.getName()+"|"+patient.getSurname()+"|"+patient.getInsuranceNumber() );
        	patientsDTO.add(patient);
        }
        return patientsDTO;
	}

	public PatientRecordViewDTO getMedicalRecords(String patientId) {
		long id = Long.parseLong(patientId);
		Patient patient = patientRepository.findById(id);
		PatientRecordViewDTO patientRecord = new PatientRecordViewDTO();
		patientRecord.setAddress(patient.getAddress() );
		patientRecord.setAge(patient.getMedicalRecord().getAge() );
		patientRecord.setBloodtype(patient.getMedicalRecord().getBloodtype().toString() );
		patientRecord.setCity(patient.getCity());
		patientRecord.setDiopter(patient.getMedicalRecord().getDiopter() );

		patientRecord.setHeight(patient.getMedicalRecord().getHeight());
		patientRecord.setInsuranceNumber(patient.getInsuranceNumber() );
		patientRecord.setName(patient.getName() );
		patientRecord.setPhoneNumber(patient.getPhoneNumber() );
		patientRecord.setRhfactor(patient.getMedicalRecord().getRhfactor().toString() );
		patientRecord.setSurname(patient.getSurname() );
		patientRecord.setWeight(patient.getMedicalRecord().getWeight() );
		
		//patientRecord.setDiseaseHistory(diseaseHistory);
		AppointmentReport report = new AppointmentReport();
		List<Appointment> appointments = patient.getApointments();
		List<DiseaseHistoryDTO> diseaseHistory = new ArrayList<DiseaseHistoryDTO>();
		PrescriptionMrDTO prescriptionDTO;
		DiseaseHistoryDTO dhItem;
		if(appointments!=null && !appointments.isEmpty())
		{
			for(int i = 0; i< appointments.size(); i++)
			{
				report = appointments.get(i).getReport();
				appointments.get(i).getReport().getPrescriptions();
				
				dhItem = new DiseaseHistoryDTO();
				dhItem.setDate(appointments.get(i).getTerm().getStartTime().toString() );
				dhItem.setDescription(report.getDescription() );
				dhItem.setDesease(report.getDiagnosis().getName() );
				dhItem.setTreatment(report.getReport() );
				
				for(Prescription prescription : appointments.get(i).getReport().getPrescriptions() )
				{ 
					prescriptionDTO = new PrescriptionMrDTO();
					prescriptionDTO.setName(prescription.getMedication().getName() );
					prescriptionDTO.setAmount(Integer.toString(prescription.getAmountPerDay() ) );
					dhItem.getPrescriptionMrDTOS().add(prescriptionDTO);
				}
				
				diseaseHistory.add(dhItem);
			}
		}
		patientRecord.setDiseaseHistory(diseaseHistory);
		return patientRecord;
	}
}
