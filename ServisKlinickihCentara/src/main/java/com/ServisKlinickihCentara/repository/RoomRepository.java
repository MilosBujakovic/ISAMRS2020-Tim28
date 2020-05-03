package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findById(long id);



}
