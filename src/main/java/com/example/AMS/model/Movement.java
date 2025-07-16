package com.example.AMS.model;

import jakarta.persistence.*;
import java.util.Date;

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
     
    @ManyToOne
    @JoinColumn(name = "from_location_id")
    private Location fromLocation;
    
    @ManyToOne
    @JoinColumn(name = "to_location_id")
    private Location toLocation;
    
    
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