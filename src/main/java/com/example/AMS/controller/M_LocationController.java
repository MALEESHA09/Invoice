package com.example.AMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.AMS.model.Location;
import com.example.AMS.model.Room;
import com.example.AMS.service.M_LocationService;

@Controller
@RequestMapping("/locations")
public class M_LocationController {

    private final M_LocationService locationService;

    @Autowired
    public M_LocationController(M_LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String listLocations(Model model) {
        model.addAttribute("locationList", locationService.getAllLocations());
        return "locations/location-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "locations/location-add";
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute Location location,
                            RedirectAttributes redirectAttributes) {
        try {
            locationService.saveLocation(location);
            redirectAttributes.addFlashAttribute("success", "Location saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save location. Please try again.");
        }
        return "redirect:/locations";
    }

   @GetMapping("/{id}")
public String viewLocation(@PathVariable String id, Model model) {
    try {
        Location location = locationService.getLocationById(id);
        if (location == null) {
            throw new IllegalArgumentException("Invalid location ID: " + id);
        }
        model.addAttribute("location", location);
        return "locations/location-view";
    } catch (Exception e) {
        // You might want to redirect to an error page or the locations list
        return "redirect:/locations?error=Location+not+found";
    }
}

@GetMapping("/edit/{id}")
public String showEditForm(@PathVariable String id, Model model) {
    try {
        Location location = locationService.getLocationById(id);
        if (location == null) {
            throw new IllegalArgumentException("Invalid location ID: " + id);
        }
        model.addAttribute("location", location);
        return "locations/location-add";
    } catch (Exception e) {
        // Redirect with error message
        return "redirect:/locations?error=Location+not+found";
    }
}
    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            locationService.deleteLocation(id);
            redirectAttributes.addFlashAttribute("success", "Location deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete location. It may be in use.");
        }
        return "redirect:/locations";
    }

    // Room management endpoints
    @PostMapping("/{locationId}/rooms")
    public String addRoom(@PathVariable String locationId,
                       @ModelAttribute Room room,
                       RedirectAttributes redirectAttributes) {
        try {
            locationService.addRoomToLocation(locationId, room);
            redirectAttributes.addFlashAttribute("success", "Room added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add room: " + e.getMessage());
        }
        return "redirect:/locations/" + locationId;
    }

    @DeleteMapping("/{locationId}/rooms/{roomId}")
    public String removeRoom(@PathVariable String locationId,
                          @PathVariable String roomId,
                          RedirectAttributes redirectAttributes) {
        try {
            locationService.removeRoomFromLocation(locationId, roomId);
            redirectAttributes.addFlashAttribute("success", "Room removed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove room: " + e.getMessage());
        }
        return "redirect:/locations/" + locationId;
    }
}