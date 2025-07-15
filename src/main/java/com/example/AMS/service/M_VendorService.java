package com.example.AMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AMS.model.Vendor;
import com.example.AMS.repository.M_VendorRepository;

@Service
@Transactional
public class M_VendorService {

    private final M_VendorRepository vendorRepository;

    public M_VendorService(M_VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // Save or update vendor
    public Vendor saveVendor(Vendor vendor) {
        if (vendor.getVendorId() == null || vendor.getVendorId().isEmpty()) {
            throw new IllegalArgumentException("Vendor ID cannot be empty");
        }
        return vendorRepository.save(vendor);
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Get vendor by ID
    public Vendor getVendorById(String vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));
    }

    // Get vendor by name (exact match)
    public Optional<Vendor> getVendorByName(String vendorName) {
        return vendorRepository.findByVendorName(vendorName);
    }

    // Search vendors by name (partial match)
    public List<Vendor> searchVendorsByName(String name) {
        return vendorRepository.searchByVendorName(name);
    }

    // Search vendors by address
    public List<Vendor> searchVendorsByAddress(String address) {
        return vendorRepository.searchByAddress(address);
    }

    // Get vendors by contact number
    public List<Vendor> getVendorsByContactNo(int contactNo) {
        return vendorRepository.findByContactNo(contactNo);
    }

    // Delete vendor
    public void deleteVendor(String vendorId) {
        if (!vendorRepository.existsByVendorId(vendorId)) {
            throw new RuntimeException("Vendor not found with ID: " + vendorId);
        }
        vendorRepository.deleteById(vendorId);
    }

    // Check if vendor exists by ID
    public boolean vendorExists(String vendorId) {
        return vendorRepository.existsByVendorId(vendorId);
    }

    // Check if vendor exists by name
    public boolean vendorNameExists(String vendorName) {
        return vendorRepository.existsByVendorName(vendorName);
    }

    // Get total vendor count
    public long getVendorCount() {
        return vendorRepository.countAllVendors();
    }
}