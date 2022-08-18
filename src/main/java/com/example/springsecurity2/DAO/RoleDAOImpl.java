package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(String role) {
        return entityManager.createQuery("select role from Role role where role.name=:role", Role.class)
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Override
    public Set<Role> findAll() {
        List<Role> list = entityManager
                .createQuery("select role from Role role", Role.class)
                .getResultList();
        return new HashSet<>(list);
    }
}
