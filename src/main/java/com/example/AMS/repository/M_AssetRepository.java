package com.example.AMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Asset;

@Repository
public interface M_AssetRepository extends JpaRepository<Asset, String> {
    List<Asset> findByNameContainingIgnoreCase(String name);
    List<Asset> findByType(String type);
    List<Asset> findByActivityStatus(boolean status);
    
    @Query("SELECT a FROM Asset a WHERE a.name LIKE %:keyword% OR a.assetId LIKE %:keyword% OR a.serialNumber LIKE %:keyword%")
    List<Asset> searchAssets(String keyword);
    
    @Query("SELECT a FROM Asset a WHERE a.invoice.invoiceId = :invoiceId")
    List<Asset> findByInvoiceId(String invoiceId);
}