package com.fleet.controller;

import com.fleet.model.User;
import com.fleet.model.Vehicle;
import com.fleet.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/vehicles")
    public String showVehicles(@RequestParam(required = false) String search,
                              HttpSession session, 
                              Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Vehicle> vehicles;
        if (search != null && !search.trim().isEmpty()) {
            vehicles = vehicleService.searchVehicles(search);
        } else {
            vehicles = vehicleService.getAvailableVehicles();
        }
        
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("search", search);
        model.addAttribute("user", user);
        return "vehicles";
    }

    @GetMapping("/vehicle/{id}")
    public String viewVehicle(@PathVariable Long id, 
                             HttpSession session, 
                             Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return "redirect:/vehicles";
        }

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("user", user);
        return "booking";
    }
}