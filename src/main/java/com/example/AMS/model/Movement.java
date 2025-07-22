package com.example.AMS.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movement {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementId;
    
    private String type;  // e.g., "Transfer", "Deployment", "Return"
    private Date date;
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
     
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    
    // Getters and Setters
    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public Location getLocation() {
    return location;
    }

    public void setLocation(Location location) {
    this.location = location;
    }



    // toString() method for debugging/logging
    @Override
    public String toString() {
        return "Movement{" +
                "movementId=" + movementId +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", asset=" + (asset != null ? asset.getAssetId() : null) +
                ", room=" + (room != null ? room.getRoomId() : null) +
                '}';
    }
}