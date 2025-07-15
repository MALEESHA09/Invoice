package com.example.AMS.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.AMS.model.Invoice;

@Repository
public interface M_InvoiceRepository extends JpaRepository<Invoice, String> {
    
    // Find invoices by vendor
    @Query("SELECT i FROM Invoice i JOIN i.vendor v WHERE v.vendorId = :vendorId")
    List<Invoice> findByVendorId(@Param("vendorId") String vendorId);
    
    // Find invoices by asset
    @Query("SELECT i FROM Invoice i JOIN i.asset a WHERE a.id = :assetId")
    List<Invoice> findByAssetId(@Param("assetId") Long assetId);
    
    // Find invoices between dates
    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate BETWEEN :startDate AND :endDate")
    List<Invoice> findInvoicesBetweenDates(@Param("startDate") Date startDate, 
                                         @Param("endDate") Date endDate);
    
    // Check if invoice exists by ID
    boolean existsByInvoiceId(String invoiceId);
}