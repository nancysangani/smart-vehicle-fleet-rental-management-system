package com.fleet.service;

import com.fleet.dao.BookingDAO;
import com.fleet.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public void createBooking(Booking booking) {
        bookingDAO.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingDAO.findById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingDAO.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByVehicleId(Long vehicleId) {
        return bookingDAO.findByVehicleId(vehicleId);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingDAO.update(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingDAO.delete(id);
    }
}