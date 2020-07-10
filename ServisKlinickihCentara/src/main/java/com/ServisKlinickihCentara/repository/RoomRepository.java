package com.ServisKlinickihCentara.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.clinics.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findById(long id);

    Room findByNumber(String number);

    ArrayList<Room> findAll();

}
