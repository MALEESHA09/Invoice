package com.example.AMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Location;

@Repository
public interface M_LocationRepository extends JpaRepository<Location, String> {
    
    // Custom query methods
    List<Location> findByDepartmentNameContainingIgnoreCase(String departmentName);
    
    @Query("SELECT a.location FROM Asset a WHERE a.assetId = :assetId")
    Location findCurrentLocationByAssetId(String assetId);
    
    @Query("SELECT l FROM Location l WHERE l.departmentName LIKE %:keyword% OR l.locationId LIKE %:keyword%")
    List<Location> searchLocations(String keyword);
}