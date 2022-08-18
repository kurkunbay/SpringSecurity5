package com.example.springsecurity2.service;

import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void createRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDAO.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findByName(name);
    }
}
