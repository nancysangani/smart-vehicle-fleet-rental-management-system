package com.fleet.service;

import com.fleet.dao.AdminDAO;
import com.fleet.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public void registerAdmin(Admin admin) {
        adminDAO.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminDAO.findById(id);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDAO.findByUsername(username);
    }

    @Override
    public Admin authenticateAdmin(String username, String password) {
        return adminDAO.authenticate(username, password);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminDAO.findAll();
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDAO.update(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminDAO.delete(id);
    }
}