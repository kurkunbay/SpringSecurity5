package com.example.springsecurity2.service;

import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public Role findRolesById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Transactional
    @Override
    public Role findRoleByName(String name) {
        return roleDAO.findRoleByName(name);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Transactional
    @Override
    public Set<Role> findRolesSetById(Long[] id) {
        return roleDAO.findRolesSetById(id);
    }

    @Override
    public Set<Role> findRoleSetByName(String[] names) {
        return roleDAO.findRoleSetByName(names);
    }
}
