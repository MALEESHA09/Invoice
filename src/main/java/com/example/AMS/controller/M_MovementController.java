package com.example.AMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AMS.model.Movement;
import com.example.AMS.service.M_AssetService;
import com.example.AMS.service.M_LocationService;
import com.example.AMS.service.M_MovementService;

@Controller
@RequestMapping("/movements")
public class M_MovementController {

    @Autowired
    private M_MovementService movementService;

    @Autowired
    private M_AssetService assetService;

    @Autowired
    private M_LocationService locationService;

    @GetMapping
    public String listMovements(Model model) {
        List<Movement> movements = movementService.getAllMovements();
        model.addAttribute("movements", movements);
        return "movements/movement";
    }

    @GetMapping("/add")
    public String addMovementForm(Model model) {
        model.addAttribute("movement", new Movement());
        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("locations", locationService.getAllLocations());
        return "movements/movement";
    }

    @PostMapping("/save")
    public String saveMovement(@ModelAttribute Movement movement) {
        movementService.saveMovement(movement);
        return "redirect:/movements";
    }

    @GetMapping("/view/{id}")
    public String viewMovement(@PathVariable Long id, Model model) {
        Movement movement = movementService.getMovementById(id);
        if (movement == null) {
            return "redirect:/movements?error=notfound";
        }
        model.addAttribute("movement", movement);
        return "movements/movement";
    }
}
