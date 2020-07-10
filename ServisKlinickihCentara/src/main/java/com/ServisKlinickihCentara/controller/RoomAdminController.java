package com.ServisKlinickihCentara.controller;

import java.util.List;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ServisKlinickihCentara.dto.appointmentsDTO.RoomAppointmentBookingDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FreeRoomDTO;
import com.ServisKlinickihCentara.service.RoomService;

import javax.websocket.server.PathParam;

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

	@RequestMapping(value="/createNewRoom", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> createNewRoom(@RequestBody RoomDTO roomDTO)
	{
		MessageDTO messageDTO = roomService.createNewRoom(roomDTO);
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);

	}

	@RequestMapping(value="/editRoom", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> editRoom(@RequestBody RoomDTO roomDTO)
	{
		MessageDTO messageDTO = roomService.editRoom(roomDTO);
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/removeRoom/{clinicId}/{roomId}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> removeRoom(@PathVariable("clinicId") String clinicId, @PathVariable("roomId") String roomId)
	{
		MessageDTO messageDTO = roomService.removeRoom(clinicId, roomId);
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
	}
}
