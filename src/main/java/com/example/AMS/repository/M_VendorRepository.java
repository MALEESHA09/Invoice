package com.example.AMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AMS.model.Vendor;

public interface M_VendorRepository extends JpaRepository<Vendor, String> {
}