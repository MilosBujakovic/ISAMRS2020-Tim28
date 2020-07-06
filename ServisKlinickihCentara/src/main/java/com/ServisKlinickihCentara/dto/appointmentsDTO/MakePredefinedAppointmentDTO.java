package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class MakePredefinedAppointmentDTO 
{
	private String startTime;
	private String endTime;
	private String dateOfCheckup;
	private long roomId;
	private long clinicId;
	private long doctorId;
	private String typeOfExam;
	
	public MakePredefinedAppointmentDTO() {}

	public MakePredefinedAppointmentDTO(String startTime, String endTime, String dateOfCheckup, long roomId,
			long clinicId, long doctorId, String typeOfExam) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.dateOfCheckup = dateOfCheckup;
		this.roomId = roomId;
		this.clinicId = clinicId;
		this.doctorId = doctorId;
		this.typeOfExam = typeOfExam;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDateOfCheckup() {
		return dateOfCheckup;
	}

	public void setDateOfCheckup(String dateOfCheckup) {
		this.dateOfCheckup = dateOfCheckup;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getClinicId() {
		return clinicId;
	}

	public void setClinicId(long clinicId) {
		this.clinicId = clinicId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getTypeOfExam() {
		return typeOfExam;
	}

	public void setTypeOfExam(String typeOfExam) {
		this.typeOfExam = typeOfExam;
	}
	
	
	
}
