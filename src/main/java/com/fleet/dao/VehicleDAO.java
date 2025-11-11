package com.fleet.dao;

import com.fleet.model.Vehicle;
import java.util.List;

public interface VehicleDAO {
    void save(Vehicle vehicle);
    Vehicle findById(Long id);
    List<Vehicle> findAll();
    List<Vehicle> findAvailable();
    List<Vehicle> searchVehicles(String keyword);
    void update(Vehicle vehicle);
    void delete(Long id);
}