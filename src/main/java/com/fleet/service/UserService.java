package com.fleet.service;

import com.fleet.model.User;
import java.util.List;

public interface UserService {
    void registerUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User authenticateUser(String username, String password);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
}