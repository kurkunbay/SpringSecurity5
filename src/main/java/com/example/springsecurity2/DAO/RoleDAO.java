package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleDAO{
    void addRoleAdmin();

    void addRoleUser();

    Role findRoleById(Long id);

    Set<Role> findRolesSetById(Long[] id);

    Role findRoleByName(String name);

    Set<Role> findRoleSetByName(String[] names);

    List<Role> getAllRoles();
}
