package com.example.AMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AMS.model.Movement;
import com.example.AMS.repository.M_MovementRepository;

@Service
public class M_MovementService {

    private final M_MovementRepository movementRepository;

    @Autowired
    public M_MovementService(M_MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Movement getMovementById(Long id) {
        Optional<Movement> movement = movementRepository.findById(id);
        return movement.orElse(null);
    }

    public Movement saveMovement(Movement movement) {
        return movementRepository.save(movement);
    }

    public void deleteMovement(Long id) {
        if (movementRepository.existsById(id)) {
            movementRepository.deleteById(id);
        }
    }
}
