package com.example.AMS.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Location;

@Repository
public interface M_LocationRepository extends JpaRepository<Location, String> {
    
    // Find locations by department name (case-insensitive contains)
    @Query("SELECT l FROM Location l WHERE LOWER(l.departmentName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Location> findByDepartmentNameContaining(@Param("name") String name);
    
    // Find active locations (where endDate is null or in future)
    @Query("SELECT l FROM Location l WHERE l.endDate IS NULL OR l.endDate >= CURRENT_DATE")
    List<Location> findActiveLocations();
    
    // Find locations by date range
    @Query("SELECT l FROM Location l WHERE l.transferDate BETWEEN :startDate AND :endDate")
    List<Location> findByTransferDateBetween(@Param("startDate") Date startDate, 
                                           @Param("endDate") Date endDate);
    
    // Find locations with rooms
    @Query("SELECT DISTINCT l FROM Location l LEFT JOIN FETCH l.rooms")
    List<Location> findAllWithRooms();
    
    // Check if location exists by ID
    boolean existsByLocationId(String locationId);
}