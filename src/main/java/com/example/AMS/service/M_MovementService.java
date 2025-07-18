package com.example.AMS.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AMS.model.Asset;
import com.example.AMS.model.Location;
import com.example.AMS.model.Movement;
import com.example.AMS.model.Room;
import com.example.AMS.repository.M_AssetRepository;
import com.example.AMS.repository.M_MovementRepository;
import com.example.AMS.repository.M_RoomRepository;

@Service
public class M_MovementService {
    private final M_MovementRepository movementRepository;
    private final M_LocationService locationService;
    private final M_AssetRepository assetRepository;
    private final M_RoomRepository roomRepository;

    @Autowired
    public M_MovementService(M_MovementRepository movementRepository,
                           M_LocationService locationService,
                           M_AssetRepository assetRepository,
                           M_RoomRepository roomRepository) {
        this.movementRepository = movementRepository;
        this.locationService = locationService;
        this.assetRepository = assetRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Movement recordMovement(String assetId, String fromLocationId, String toLocationId,
                                 String movementType, String roomId, Date date, String notes) {
        // Validate assets and locations
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        
        Location fromLocation = locationService.getLocationById(fromLocationId);
        Location toLocation = locationService.getLocationById(toLocationId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        // Create movement record
        Movement movement = new Movement();
        movement.setAsset(asset);
        movement.setType(movementType);
        movement.setDate(date != null ? date : new Date()); // Use current date if null
        movement.setNotes(notes);
        movement.setRoom(room);
        
        // Update asset's current location
        asset.setLocation(toLocation);
        assetRepository.save(asset);
        
        return movementRepository.save(movement);
    }

    public List<Movement> getMovementHistoryForAsset(String assetId) {
        return movementRepository.findByAsset_AssetIdOrderByDateDesc(assetId);
    }

    public List<Movement> getMovementsByLocation(String locationId) {
        return movementRepository.findByFromLocation_LocationIdOrToLocation_LocationId(locationId, locationId);
    }

    public List<Movement> getMovementsByDateRange(Date startDate, Date endDate) {
        return movementRepository.findByDateBetween(startDate, endDate);
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public void saveMovement(String assetId, String roomId, String locationDetails, String type, Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveMovement'");
    }

    public Object getMovementsBy(Long movementId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovementsBy'");
    }
}