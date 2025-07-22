package com.example.AMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AMS.model.Location;

public interface M_LocationRepository extends JpaRepository<Location, Long> {
    // No need to redeclare deleteById, findById, or existsById
}
