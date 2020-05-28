package com.ServisKlinickihCentara.model.clinics;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class Term 
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JsonManagedReference
    private Room room;
    
    @Column
    private Timestamp startTime;
    
    @Column
    private Timestamp endTime;
    

    
    public Term() {}
    
    

	public Term(Long id, Room room, Timestamp startTime, Timestamp endTime)
	{
		super();
		this.id = id;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Room getRoom() 
	{
		return room;
	}

	public void setRoom(Room room) 
	{
		this.room = room;
	}

	public Timestamp getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(Timestamp startTime) 
	{
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) 
	{
		this.endTime = endTime;
	}

}
