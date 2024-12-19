package com.hotelbooking.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.hotelbooking.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
