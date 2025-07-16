package com.example.AMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Room;

@Repository
public interface M_RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByRoomNameContainingIgnoreCase(String roomName);
    List<Room> findByLocationLocationId(String locationId);
    
    @Query("SELECT r FROM Room r WHERE r.roomName LIKE %:keyword% OR r.roomId LIKE %:keyword% OR r.location.locationId LIKE %:keyword%")
    List<Room> searchRooms(String keyword);
}