package com.fleet.dao;

import com.fleet.model.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User findById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
    User authenticate(String username, String password);
}