package com.example.springsecurity2.service;

import com.example.springsecurity2.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findRolesById(Long id);

    Role findRoleByName(String name);

    Set<Role> findRolesSetById(Long[] id);

    Set<Role> findRoleSetByName(String[] names);

    List<Role> getAllRoles();
}
