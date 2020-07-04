package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class AppointmentTermDTO 
{
	private String startTime;
	private String endTime;
	private String typeOfExam;
	
	public AppointmentTermDTO() {}

	public AppointmentTermDTO(String startTime, String endTime, String typeOfExam) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
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

	public String getTypeOfExam() {
		return typeOfExam;
	}

	public void setTypeOfExam(String typeOfExam) {
		this.typeOfExam = typeOfExam;
	}
	
	
}