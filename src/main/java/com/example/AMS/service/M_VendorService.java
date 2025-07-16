package com.example.AMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AMS.model.Vendor;
import com.example.AMS.repository.M_VendorRepository;

@Service
public class M_VendorService {
    private final M_VendorRepository vendorRepository;

    public M_VendorService(M_VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(String vendorId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVendorById'");
    }
}