package com.example.AMS.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Room {
    @Id
    private String roomId;
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "location_Id")
    private Location location;

    // Getters and Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}
