package com.fleet.dao;

import com.fleet.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            return sessionFactory.openSession();
        }
    }

    @Override
    public void save(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.save(user);
        } catch (Exception e) {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
        }
    }

    @Override
    public User findById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            return session.get(User.class, id);
        } catch (Exception e) {
            session = sessionFactory.openSession();
            User user = session.get(User.class, id);
            session.close();
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            session.close();
            return user;
        }
    }

    @Override
    public User findByEmail(String email) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            session.close();
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            session = sessionFactory.openSession();
            List<User> users = session.createQuery("FROM User", User.class).list();
            session.close();
            return users;
        }
    }

    @Override
    public void update(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.update(user);
        } catch (Exception e) {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.getCurrentSession();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
        } catch (Exception e) {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
            session.close();
        }
    }

    @Override
    public User authenticate(String username, String password) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery(
                "FROM User WHERE username = :username AND password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (Exception e) {
            session = sessionFactory.openSession();
            Query<User> query = session.createQuery(
                "FROM User WHERE username = :username AND password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = query.uniqueResult();
            session.close();
            return user;
        }
    }
}