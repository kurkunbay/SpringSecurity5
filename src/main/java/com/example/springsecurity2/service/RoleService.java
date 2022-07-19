package com.example.springsecurity2.service;

import com.example.springsecurity2.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();
}
