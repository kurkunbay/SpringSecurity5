package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleDAO{
    Role findByName(String name);
    void save(Role role);
    void delete(Role role);
    Set<Role> findAll();
}
