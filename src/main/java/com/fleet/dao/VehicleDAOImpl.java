package com.fleet.dao;

import com.fleet.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Vehicle vehicle) {
        getSession().save(vehicle);
    }

    @Override
    public Vehicle findById(Long id) {
        return getSession().get(Vehicle.class, id);
    }

    @Override
    public List<Vehicle> findAll() {
        return getSession().createQuery("FROM Vehicle ORDER BY id DESC", Vehicle.class).list();
    }

    @Override
    public List<Vehicle> findAvailable() {
        Query<Vehicle> query = getSession().createQuery(
            "FROM Vehicle WHERE status = :status ORDER BY id DESC", Vehicle.class);
        query.setParameter("status", "AVAILABLE");
        return query.list();
    }

    @Override
    public List<Vehicle> searchVehicles(String keyword) {
        Query<Vehicle> query = getSession().createQuery(
            "FROM Vehicle WHERE (make LIKE :keyword OR model LIKE :keyword OR vehicleType LIKE :keyword) " +
            "AND status = 'AVAILABLE' ORDER BY id DESC", Vehicle.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.list();
    }

    @Override
    public void update(Vehicle vehicle) {
        getSession().update(vehicle);
    }

    @Override
    public void delete(Long id) {
        Vehicle vehicle = findById(id);
        if (vehicle != null) {
            getSession().delete(vehicle);
        }
    }
}