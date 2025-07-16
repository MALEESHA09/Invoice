package com.example.AMS.repository;

import com.example.AMS.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface M_MovementRepository extends JpaRepository<Movement, Long> {

    // Find movements by asset ID
    List<Movement> findByAsset_AssetId(String assetId);

    // Find movements by room ID
    List<Movement> findByRoom_RoomId(String roomId);

    // Find movements by type (e.g., "Transfer", "Deployment")
    List<Movement> findByType(String type);

    // Find movements within a date range
    List<Movement> findByDateBetween(Date startDate, Date endDate);

     List<Movement> findByAsset_AssetIdOrderByDateDesc(String assetId);
    List<Movement> findByFromLocation_LocationIdOrToLocation_LocationId(String fromLocationId, String toLocationId);
    

    // Find movements by asset ID and date range
    @Query("SELECT m FROM Movement m WHERE m.asset.assetId = :assetId AND m.date BETWEEN :startDate AND :endDate")
    List<Movement> findMovementsByAssetAndDateRange(
            @Param("assetId") String assetId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    // Find current location of an asset (most recent movement)
    @Query("SELECT m FROM Movement m WHERE m.asset.assetId = :assetId ORDER BY m.date DESC LIMIT 1")
    Movement findCurrentLocationOfAsset(@Param("assetId") String assetId);

    // Count movements by type
    @Query("SELECT m.type, COUNT(m) FROM Movement m GROUP BY m.type")
    List<Object[]> countMovementsByType();
}