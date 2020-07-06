package com.ServisKlinickihCentara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.appointmentsDTO.RoomAppointmentBookingDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FreeRoomDTO;
import com.ServisKlinickihCentara.service.RoomService;

@RestController
@RequestMapping( value = "/clinicAdmin/roomAdmin")
public class RoomAdminController 
{
	
	@Autowired
	RoomService roomService;
	
	@RequestMapping(value="/findFreeClinicRooms", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FreeRoomDTO>> findFreeClinicRooms(@RequestBody RoomAppointmentBookingDTO appointmentSlot) throws NullPointerException
	{
		
		List<FreeRoomDTO> freeRooms = roomService.findFreeClinicRooms(appointmentSlot);
		if(freeRooms==null || freeRooms.isEmpty())
		{
			//freeRooms = new ArrayList<FreeRoomDTO>();
			System.out.println("No free rooms found!");
			return new ResponseEntity<List<FreeRoomDTO>>(freeRooms, HttpStatus.NOT_FOUND);
		}
		else
		{
			System.out.println("Free rooms found!");
			return new ResponseEntity<List<FreeRoomDTO>>(freeRooms, HttpStatus.OK);
		}
	}
}
