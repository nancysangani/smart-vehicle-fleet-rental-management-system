package com.fleet.controller;

import com.fleet.model.Booking;
import com.fleet.model.User;
import com.fleet.model.Vehicle;
import com.fleet.service.BookingService;
import com.fleet.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/bookVehicle")
    public String bookVehicle(@RequestParam Long vehicleId,
                             @RequestParam String startDate,
                             @RequestParam String endDate,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            if (vehicle == null || !"AVAILABLE".equals(vehicle.getStatus())) {
                model.addAttribute("error", "Vehicle not available");
                return "redirect:/vehicles";
            }

            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            
            if (start.isBefore(LocalDate.now())) {
                model.addAttribute("error", "Start date cannot be in the past");
                model.addAttribute("vehicle", vehicle);
                return "booking";
            }

            if (end.isBefore(start)) {
                model.addAttribute("error", "End date must be after start date");
                model.addAttribute("vehicle", vehicle);
                return "booking";
            }

            long days = ChronoUnit.DAYS.between(start, end) + 1;
            BigDecimal totalAmount = vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));

            Booking booking = new Booking(user, vehicle, start, end, totalAmount);
            booking.setStatus("CONFIRMED");
            bookingService.createBooking(booking);

            vehicle.setStatus("BOOKED");
            vehicleService.updateVehicle(vehicle);

            model.addAttribute("success", "Booking confirmed successfully!");
            return "redirect:/myBookings";
        } catch (Exception e) {
            model.addAttribute("error", "Booking failed: " + e.getMessage());
            return "redirect:/vehicles";
        }
    }

    @GetMapping("/myBookings")
    public String myBookings(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingService.getBookingsByUserId(user.getId());
        model.addAttribute("bookings", bookings);
        model.addAttribute("user", user);
        return "myBookings";
    }

    @PostMapping("/cancelBooking/{id}")
    public String cancelBooking(@PathVariable Long id, 
                               HttpSession session, 
                               Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            Booking booking = bookingService.getBookingById(id);
            if (booking != null && booking.getUser().getId().equals(user.getId())) {
                Vehicle vehicle = booking.getVehicle();
                vehicle.setStatus("AVAILABLE");
                vehicleService.updateVehicle(vehicle);
                
                booking.setStatus("CANCELLED");
                bookingService.updateBooking(booking);
                
                model.addAttribute("success", "Booking cancelled successfully");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Failed to cancel booking");
        }

        return "redirect:/myBookings";
    }
}