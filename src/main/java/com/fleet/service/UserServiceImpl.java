package com.fleet.service;

import com.fleet.dao.UserDAO;
import com.fleet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void registerUser(User user) {
        userDAO.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User authenticateUser(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.delete(id);
    }
}