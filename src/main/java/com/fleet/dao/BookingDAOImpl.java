package com.fleet.dao;

import com.fleet.model.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(booking);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Booking findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Booking.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Booking> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Booking ORDER BY bookingDate DESC", Booking.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Booking> query = session.createQuery(
                "FROM Booking WHERE user.id = :userId ORDER BY bookingDate DESC", Booking.class);
            query.setParameter("userId", userId);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Booking> findByVehicleId(Long vehicleId) {
        Session session = sessionFactory.openSession();
        try {
            Query<Booking> query = session.createQuery(
                "FROM Booking WHERE vehicle.id = :vehicleId ORDER BY bookingDate DESC", Booking.class);
            query.setParameter("vehicleId", vehicleId);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(booking);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Booking booking = session.get(Booking.class, id);
            if (booking != null) {
                session.delete(booking);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}