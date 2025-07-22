package com.example.AMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AMS.model.Location;
import com.example.AMS.repository.M_LocationRepository;

@Service
public class M_LocationService {

    @Autowired
    private M_LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
            .orElseThrow(() -> new RuntimeException("Location not found with id: " + locationId));
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }
}
