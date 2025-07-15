package com.example.AMS.repository;

import com.example.AMS.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface M_AssetRepository extends JpaRepository<Asset, String> {

    // Find assets by name (case-insensitive contains)
    @Query("SELECT a FROM Asset a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Asset> findByNameContaining(@Param("name") String name);
    
    // Find assets by type
    List<Asset> findByType(String type);
    
    // Find assets by brand
    List<Asset> findByBrand(String brand);
    
    // Find assets by activity status
    List<Asset> findByActivityStatus(boolean activityStatus);
    
    // Find assets purchased between dates
    @Query("SELECT a FROM Asset a WHERE a.purchaseDate BETWEEN :startDate AND :endDate")
    List<Asset> findByPurchaseDateBetween(@Param("startDate") Date startDate, 
                                        @Param("endDate") Date endDate);
    
    // Find assets by location
    @Query("SELECT a FROM Asset a WHERE a.location.locationId = :locationId")
    List<Asset> findByLocationId(@Param("locationId") String locationId);
    
    // Find assets under warranty
    @Query("SELECT a FROM Asset a WHERE a.warrantyDate >= CURRENT_DATE")
    List<Asset> findUnderWarranty();
    
    // Count assets by type
    @Query("SELECT a.type, COUNT(a) FROM Asset a GROUP BY a.type")
    List<Object[]> countAssetsByType();
    
    // Check if asset exists by ID
    boolean existsByAssetId(String assetId);
}