package com.ServisKlinickihCentara.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.RoomDTO;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.dto.appointmentsDTO.RoomAppointmentBookingDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FreeRoomDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.Room;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.repository.AppointmentRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ClinicRepository clinicRepository;

	public List<FreeRoomDTO> findFreeClinicRooms(RoomAppointmentBookingDTO appointmentSlot) {
		Clinic clinic = clinicRepository.findById(Long.parseLong(appointmentSlot.getClinicId() ) );
		List<Room> rooms = roomRepository.findAll();
		int j=0;
		do
		{
			if(rooms.get(j).getClinic().getId()!=clinic.getId())
			{
				rooms.remove(j);
			}
			else j++;
		}
		while(j<rooms.size());
		
		List<Appointment> appointments = clinic.getAppointments();
		List<Term> terms = new ArrayList<Term>();
		for(int i = 0; i < appointments.size(); i++)
		{
			terms.add(appointments.get(i).getTerm() );
		}
		List<FreeRoomDTO> freeRooms = new ArrayList<FreeRoomDTO>();
		Timestamp startTime = createTimestamp(appointmentSlot.getDate(), appointmentSlot.getStartTime() );
		Timestamp endTime = createTimestamp(appointmentSlot.getDate(), appointmentSlot.getEndTime() );
		for(int i = 0; i<terms.size(); i++ )
		{
			System.out.println("Reservation: "+startTime+ " to "+ endTime);
			System.out.println("Comparator:"+ terms.get(i).getStartTime() + " to "+ terms.get(i).getEndTime() );
			if( (startTime.before(terms.get(i).getEndTime() ) && startTime.after(terms.get(i).getStartTime() ) ) ||
				/*TODO: Pocetak odabranog je izmedju pocetka i kraja termina*/
				(endTime.before(terms.get(i).getEndTime() ) && endTime.after(terms.get(i).getStartTime() ) ) ||
				/*TODO: Kraj odabranog je izmedju pocetka i kraja termina*/
				(startTime.after(terms.get(i).getStartTime() ) && endTime.before(terms.get(i).getEndTime()   ) ) ||
				/*TODO: I pocetak  i kraj odabrang izmedju pocetka i kraja termina*/
				(startTime.before(terms.get(i).getStartTime() ) && endTime.after(terms.get(i).getEndTime()   ) ) ||
				/*TODO: Pocetak i kraj odabranog su prekinuti terminom koji je u potpunosti izmedju njih*/
				(startTime.after(terms.get(i).getStartTime() ) && endTime.before(terms.get(i).getEndTime()   ) ) ||
				/*TODO: I pocetak i kraj odabranog unutar termina*/
				(startTime.equals(terms.get(i).getStartTime() ) || endTime.equals(terms.get(i).getEndTime()  ) ) )
				/*TODO: Granice odabranog i termina istovremene*/
			{
				System.out.println("Overlapping discovered!");
				for(j = 0; j < rooms.size(); j++)
				{
					if(terms.get(i).getRoom().equals(rooms.get(j) ) )
					{
						rooms.remove(j);
						System.out.println("A Room isn't vacant!");
						break;
					}
				}
			}
			else
			{
				//TODO: Ostali u redu
			}
		}
		FreeRoomDTO freeRoom;
		for(int i = 0; i < rooms.size(); i++)
		{
			freeRoom = new FreeRoomDTO(rooms.get(i).getId(), rooms.get(i).getNumber() );
			freeRooms.add(freeRoom);
		}
		return freeRooms;
	}

	public MessageDTO createNewRoom(RoomDTO roomDTO)
	{
		Room room = roomRepository.findByNumber(roomDTO.getNumber());

		if (room != null){
			if (room.getClinic().getId() == Long.parseLong(roomDTO.getClinicId())) {
				return new MessageDTO("Room with this name already exists in this clinic!!!", false);
			}
		}

		Clinic clinic = clinicRepository.findById(Long.parseLong(roomDTO.getClinicId()));
		Room r = new Room(roomDTO.getNumber(), clinic);
		clinic.getRooms().add(r);

		clinicRepository.save(clinic);
		//roomRepository.save(r);
		return new MessageDTO("You successfully created new room :)", true);

	}

	public MessageDTO editRoom(RoomDTO roomDTO)
	{
		Room room = roomRepository.findById(Long.parseLong(roomDTO.getId()));
		Room r = roomRepository.findByNumber(roomDTO.getNumber());

		if (r != null){
			if (r.getId() != room.getId()){
				return new MessageDTO("Room with this name already exists in this clinic!!!", false);
			}
		}

		room.setNumber(roomDTO.getNumber());
		roomRepository.save(r);
		return new MessageDTO("You successfully edited room :)", true);

	}

	public MessageDTO removeRoom(String clinicId, String roomId)
	{
		Clinic clinic = clinicRepository.findById(Long.parseLong(clinicId));
		Room room = roomRepository.findById(Long.parseLong(roomId));

		ArrayList<Appointment> appointments = clinic.getAppointments()
				.stream().filter(a -> a.getTerm().getRoom().getId() == room.getId()
						&& a.getTerm().getStartTime().after(new Timestamp(System.currentTimeMillis())))
				.collect(Collectors.toCollection(ArrayList::new));

		if(appointments.size() > 0){
			return new MessageDTO("You cannot delete room because it will be appointment in the future :)", false);
		}

		clinic.getRooms().remove(room);
		clinicRepository.save(clinic);

		//roomRepository.delete(room);
		return new MessageDTO("You successfully deleted room :)", true);

	}
	
	
	public Timestamp createTimestamp(String date, String time)
	{
		//LocalDate.parse(date);
		//Time.valueOf(time);
		Timestamp t = Timestamp.valueOf(date+" "+time);
		System.out.println("Timestamp is: " + t);
		return t;
	}
}
