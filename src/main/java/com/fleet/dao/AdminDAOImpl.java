package com.fleet.dao;

import com.fleet.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(admin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Admin findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Admin.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public Admin findByUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username", Admin.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Admin> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Admin", Admin.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(admin);
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
            Admin admin = session.get(Admin.class, id);
            if (admin != null) {
                session.delete(admin);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Admin authenticate(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            Query<Admin> query = session.createQuery(
                "FROM Admin WHERE username = :username AND password = :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }
}