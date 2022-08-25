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
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoleAdmin() {
        Role admin = new Role(1L, "ROLE_ADMIN");
        entityManager.persist(admin);
    }

    @Override
    public void addRoleUser() {
        Role user = new Role(2L, "ROLE_USER");
        entityManager.persist(user);
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Set<Role> findRolesSetById(Long[] id) {
        TypedQuery<Role> query = entityManager
                .createQuery("select r from Role r where  r.id IN :id", Role.class)
                .setParameter("id", Arrays.asList(id));
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager
                .createQuery("select r from Role r where  r.name =:name", Role.class)
                .setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public Set<Role> findRoleSetByName(String[] names) {
        TypedQuery<Role> query = entityManager
                .createQuery("select r from Role r where  r.name IN :names", Role.class)
                .setParameter("names", Arrays.asList(names));
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> typedQuery = entityManager.createQuery("SELECT r FROM  Role r", Role.class);
        return typedQuery.getResultList();
    }
}