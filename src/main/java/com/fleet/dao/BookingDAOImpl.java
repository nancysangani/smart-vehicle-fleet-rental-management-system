package com.fleet.dao;

import com.fleet.model.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Booking booking) {
        getSession().save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return getSession().get(Booking.class, id);
    }

    @Override
    public List<Booking> findAll() {
        return getSession().createQuery("FROM Booking ORDER BY bookingDate DESC", Booking.class).list();
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        Query<Booking> query = getSession().createQuery(
            "FROM Booking WHERE user.id = :userId ORDER BY bookingDate DESC", Booking.class);
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public List<Booking> findByVehicleId(Long vehicleId) {
        Query<Booking> query = getSession().createQuery(
            "FROM Booking WHERE vehicle.id = :vehicleId ORDER BY bookingDate DESC", Booking.class);
        query.setParameter("vehicleId", vehicleId);
        return query.list();
    }

    @Override
    public void update(Booking booking) {
        getSession().update(booking);
    }

    @Override
    public void delete(Long id) {
        Booking booking = findById(id);
        if (booking != null) {
            getSession().delete(booking);
        }
    }
}