package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class RoomAppointmentBookingDTO 
{
	private String date;
	private String startTime;
	private String endTime;
	private String clinicId;
	
	public RoomAppointmentBookingDTO() {}

	public RoomAppointmentBookingDTO(String date, String startTime, String endTime, String clinicId) 
	{
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.clinicId = clinicId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	
	
	
}
