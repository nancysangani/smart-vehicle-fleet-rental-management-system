package com.fleet.service;

import com.fleet.model.Admin;
import java.util.List;

public interface AdminService {
    void registerAdmin(Admin admin);
    Admin getAdminById(Long id);
    Admin getAdminByUsername(String username);
    Admin authenticateAdmin(String username, String password);
    List<Admin> getAllAdmins();
    void updateAdmin(Admin admin);
    void deleteAdmin(Long id);
}