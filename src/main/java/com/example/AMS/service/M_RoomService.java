package com.example.AMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AMS.model.Room;
import com.example.AMS.repository.M_RoomRepository;

@Service
public class M_RoomService {
    private final M_RoomRepository roomRepository;

    @Autowired
    public M_RoomService(M_RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(String roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }

    public List<Room> searchRooms(String keyword) {
        return roomRepository.searchRooms(keyword);
    }

    public List<Room> findByRoomName(String roomName) {
        return roomRepository.findByRoomNameContainingIgnoreCase(roomName);
    }

    public List<Room> findByLocationId(String locationId) {
        return roomRepository.findByLocationLocationId(locationId);
    }
}