package com.example.AMS.repository;

import com.example.AMS.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface M_VendorRepository extends JpaRepository<Vendor, String> {

    // Find vendor by exact name match
    Optional<Vendor> findByVendorName(String vendorName);
    
    // Search vendors by name (case-insensitive contains)
    @Query("SELECT v FROM Vendor v WHERE LOWER(v.vendorName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Vendor> searchByVendorName(@Param("name") String name);
    
    // Find vendors by address (case-insensitive contains)
    @Query("SELECT v FROM Vendor v WHERE LOWER(v.address) LIKE LOWER(CONCAT('%', :address, '%'))")
    List<Vendor> searchByAddress(@Param("address") String address);
    
    // Find vendors by contact number
    List<Vendor> findByContactNo(int contactNo);
    
    // Check if vendor exists by ID
    boolean existsByVendorId(String vendorId);
    
    // Check if vendor exists by name
    boolean existsByVendorName(String vendorName);
    
    // Count all vendors
    @Query("SELECT COUNT(v) FROM Vendor v")
    long countAllVendors();
}