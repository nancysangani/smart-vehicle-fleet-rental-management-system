package com.fleet.dao;

import com.fleet.model.Admin;
import java.util.List;

public interface AdminDAO {
    void save(Admin admin);
    Admin findById(Long id);
    Admin findByUsername(String username);
    List<Admin> findAll();
    void update(Admin admin);
    void delete(Long id);
    Admin authenticate(String username, String password);
}