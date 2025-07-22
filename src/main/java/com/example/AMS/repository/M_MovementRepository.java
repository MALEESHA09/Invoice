package com.example.AMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AMS.model.Movement;

public interface M_MovementRepository extends JpaRepository<Movement, Long> {

    Optional<Movement> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
