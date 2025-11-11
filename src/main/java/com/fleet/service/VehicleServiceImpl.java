package com.fleet.service;

import com.fleet.dao.VehicleDAO;
import com.fleet.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleDAO.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleDAO.findById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDAO.findAvailable();
    }

    @Override
    public List<Vehicle> searchVehicles(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAvailableVehicles();
        }
        return vehicleDAO.searchVehicles(keyword);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.update(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleDAO.delete(id);
    }
}