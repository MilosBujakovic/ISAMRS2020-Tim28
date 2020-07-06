package com.ServisKlinickihCentara.dto.clinicsDTO;

public class FreeRoomDTO 
{
	private long id;
	private String number;
	
	public FreeRoomDTO() {}

	public FreeRoomDTO(long id, String number) {
		super();
		this.id = id;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
