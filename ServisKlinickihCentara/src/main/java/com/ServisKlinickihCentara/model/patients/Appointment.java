package com.ServisKlinickihCentara.model.patients;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.Employee;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Appointment 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonManagedReference
	private Patient patient;
	
	@OneToOne
	private Term term;

	/*@ManyToOne
	@JsonManagedReference
	private Doctor doctor;*/

	@ManyToOne
	@JsonManagedReference
	private Clinic clinic;


	@ManyToOne
	@JsonManagedReference
	private Employee employee;


	@Column
	private AppointmentType type;

	@ManyToOne
	private TypeOfExam typeOfExam;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "report_id", referencedColumnName = "id")
	private AppointmentReport report;
	
	@Column
	private boolean predefined;
	
	@Column
	private boolean active;
	
	@Column
	private boolean cancelled;

	public Appointment(){
		super();
	}

	public Appointment(Long id, Patient patient, Term term, Employee employee, AppointmentType type,
			AppointmentReport report, Clinic clinic, boolean predefined, boolean active, boolean cancelled) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		//this.doctor = doctor;
		this.employee = employee;
		this.type = type;
		this.report = report;
		this.clinic = clinic;
		this.predefined = predefined;
		this.active = active;
		this.cancelled = cancelled;
	}

	public Appointment(Patient patient, Term term,Employee employee, AppointmentType type,
			AppointmentReport report,Clinic clinic, boolean predefined, boolean active, boolean cancelled) {
		super();
		this.patient = patient;
		this.term = term;
		this.employee = employee;
		this.type = type;
		this.report = report;
		this.clinic = clinic;
		this.predefined = predefined;
		this.active = active;
		this.cancelled = cancelled;
	}

	public Appointment(Patient patient, Term term, Employee employee, AppointmentType type,
			AppointmentReport report,Clinic clinic, boolean predefined) {
		super();
		this.patient = patient;
		this.term = term;
		this.employee = employee;
		this.type = type;
		this.report = report;
		this.clinic = clinic;
		this.predefined = predefined;
		this.active = true;
		this.cancelled = false;
	}

	public Appointment(Patient patient, Term term, Employee employee, AppointmentType type,
			AppointmentReport report, Clinic clinic) {
		super();
		this.patient = patient;
		this.term = term;
		this.employee = employee;
		this.type = type;
		this.report = report;
		this.clinic = clinic;
		this.predefined = false;
		this.active = true;
		this.cancelled = false;
	}

	public Appointment(Long id, Patient patient, Term term, Clinic clinic, Employee employee, AppointmentType type, TypeOfExam typeOfExam, AppointmentReport report, boolean predefined, boolean active, boolean cancelled) {
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.clinic = clinic;
		this.employee = employee;
		this.type = type;
		this.typeOfExam = typeOfExam;
		this.report = report;
		this.predefined = predefined;
		this.active = active;
		this.cancelled = cancelled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	/*public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}*/

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TypeOfExam getTypeOfExam() {
		return typeOfExam;
	}

	public void setTypeOfExam(TypeOfExam typeOfExam) {
		this.typeOfExam = typeOfExam;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public AppointmentReport getReport() {
		return report;
	}

	public void setReport(AppointmentReport report) {
		this.report = report;
	}

	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}


	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", patient=" + patient.getId() +
				", term=" + term.getStartTime() +
				", clinic=" + clinic.getId() +
				", employee=" + employee.getEmail() +
				", type=" + type.toString() +
				", typeOfExam=" + typeOfExam.getName() +
				", report=" + report.getId() +
				", predefined=" + predefined +
				", active=" + active +
				", cancelled=" + cancelled +
				'}';
	}
}
