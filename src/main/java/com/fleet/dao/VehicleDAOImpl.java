package com.fleet.dao;

import com.fleet.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(vehicle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Vehicle findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Vehicle.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Vehicle> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Vehicle ORDER BY id DESC", Vehicle.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Vehicle> findAvailable() {
        Session session = sessionFactory.openSession();
        try {
            Query<Vehicle> query = session.createQuery(
                "FROM Vehicle WHERE status = :status ORDER BY id DESC", Vehicle.class);
            query.setParameter("status", "AVAILABLE");
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Vehicle> searchVehicles(String keyword) {
        Session session = sessionFactory.openSession();
        try {
            Query<Vehicle> query = session.createQuery(
                "FROM Vehicle WHERE (make LIKE :keyword OR model LIKE :keyword OR vehicleType LIKE :keyword) " +
                "AND status = 'AVAILABLE' ORDER BY id DESC", Vehicle.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(vehicle);
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
            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
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