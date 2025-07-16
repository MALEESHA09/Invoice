package com.example.AMS.service;

import com.example.AMS.model.Location;
import com.example.AMS.repository.M_LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class M_LocationService {
    private final M_LocationRepository locationRepository;

    @Autowired
    public M_LocationService(M_LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Basic CRUD operations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(String locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + locationId));
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(String locationId) {
        locationRepository.deleteById(locationId);
    }

    // Special methods for Movement integration
    public List<Location> getLocationsByDepartment(String departmentName) {
        return locationRepository.findByDepartmentNameContainingIgnoreCase(departmentName);
    }

    public Location getCurrentLocationForAsset(String assetId) {
        return locationRepository.findCurrentLocationByAssetId(assetId);
    }
}