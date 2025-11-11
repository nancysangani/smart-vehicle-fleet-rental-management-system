package com.fleet.service;

import com.fleet.model.Vehicle;
import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    List<Vehicle> searchVehicles(String keyword);
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);
}