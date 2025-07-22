package com.example.AMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Room;

@Repository
public interface M_RoomRepository extends JpaRepository<Room, String> {
    // Add custom queries if needed
}
