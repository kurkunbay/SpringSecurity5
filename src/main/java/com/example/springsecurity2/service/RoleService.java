package com.example.springsecurity2.service;

import com.example.springsecurity2.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void createRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);


    Role getRoleByName(String name);

    Set<Role> getAllRoles();
}
