package com.example.AMS.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AMS.repository.M_AssetRepository;
import com.example.AMS.repository.M_RoomRepository;
import com.example.AMS.service.M_LocationService;
import com.example.AMS.service.M_MovementService;

@Controller
@RequestMapping("/Movement")
public class M_MovementController {
    private final M_MovementService movementService;
    private final M_LocationService locationService;
    private final M_AssetRepository assetRepository;
    private final M_RoomRepository roomRepository;

    @Autowired
    public M_MovementController(M_MovementService movementService,
                              M_LocationService locationService,
                              M_AssetRepository assetRepository,
                              M_RoomRepository roomRepository) {
        this.movementService = movementService;
        this.locationService = locationService;
        this.assetRepository = assetRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public String showMovementPage(Model model) {
        model.addAttribute("movements", movementService.getAllMovements());
        model.addAttribute("assets", assetRepository.findAll());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("rooms", roomRepository.findAll());
        return "Movement/movement";
    }

    @PostMapping("/record")
    public String recordMovement(@RequestParam String assetId,
                               @RequestParam String fromLocationId,
                               @RequestParam String toLocationId,
                               @RequestParam String movementType,
                               @RequestParam String roomId,
                               @RequestParam Date date,
                               @RequestParam(required = false) String notes) {
        
        movementService.recordMovement(assetId, fromLocationId, toLocationId, 
                                     movementType, roomId, date, notes);
        return "redirect:/Movement";
    }

    @GetMapping("/asset/{assetId}")
    public String getAssetMovementHistory(@PathVariable String assetId, Model model) {
        model.addAttribute("movements", movementService.getMovementHistoryForAsset(assetId));
        model.addAttribute("asset", assetRepository.findById(assetId).orElseThrow());
        return "Movement/asset_movement_history";
    }
}