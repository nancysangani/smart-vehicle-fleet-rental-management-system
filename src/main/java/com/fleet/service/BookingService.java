package com.fleet.service;

import com.fleet.model.Booking;
import java.util.List;

public interface BookingService {
    void createBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByVehicleId(Long vehicleId);
    void updateBooking(Booking booking);
    void deleteBooking(Long id);
}
