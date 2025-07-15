package com.example.AMS.service;

import com.example.AMS.model.Location;
import com.example.AMS.model.Room;
import com.example.AMS.repository.M_LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class M_LocationService {

    private final M_LocationRepository locationRepository;

    @Autowired
    public M_LocationService(M_LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Save or update location with rooms
    public Location saveLocation(Location location) {
        validateLocation(location);
        // Ensure room-location relationship is maintained
        if (location.getRooms() != null) {
            location.getRooms().forEach(room -> room.setLocation(location));
        }
        return locationRepository.save(location);
    }

    // Get all locations with their rooms
    public List<Location> getAllLocations() {
        return locationRepository.findAllWithRooms();
    }

    // Get location by ID with rooms
    public Location getLocationById(String locationId) {
        return locationRepository.findById(locationId)
                .map(location -> {
                    location.getRooms().size(); // Initialize rooms collection
                    return location;
                })
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + locationId));
    }

    // Search locations by department name
    public List<Location> searchLocationsByDepartment(String name) {
        return locationRepository.findByDepartmentNameContaining(name);
    }

    // Get active locations
    public List<Location> getActiveLocations() {
        return locationRepository.findActiveLocations();
    }

    // Get locations by transfer date range
    public List<Location> getLocationsByTransferDateRange(Date startDate, Date endDate) {
        return locationRepository.findByTransferDateBetween(startDate, endDate);
    }

    // Delete location (will cascade to rooms)
    public void deleteLocation(String locationId) {
        if (!locationRepository.existsByLocationId(locationId)) {
            throw new RuntimeException("Location not found with ID: " + locationId);
        }
        locationRepository.deleteById(locationId);
    }

    // Add room to location
    public Location addRoomToLocation(String locationId, Room room) {
        Location location = getLocationById(locationId);
        location.addRoom(room);
        return locationRepository.save(location);
    }

    // Remove room from location
    public Location removeRoomFromLocation(String locationId, String roomId) {
        Location location = getLocationById(locationId);
        location.getRooms().removeIf(room -> room.getRoomId().equals(roomId));
        return locationRepository.save(location);
    }

    // Validate location
    private void validateLocation(Location location) {
        if (location.getDepartmentName() == null || location.getDepartmentName().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty");
        }
        if (location.getStartDate() != null && location.getEndDate() != null 
            && location.getEndDate().before(location.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }
}