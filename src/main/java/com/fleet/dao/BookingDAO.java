package com.fleet.dao;

import com.fleet.model.Booking;
import java.util.List;

public interface BookingDAO {
    void save(Booking booking);
    Booking findById(Long id);
    List<Booking> findAll();
    List<Booking> findByUserId(Long userId);
    List<Booking> findByVehicleId(Long vehicleId);
    void update(Booking booking);
    void delete(Long id);
}