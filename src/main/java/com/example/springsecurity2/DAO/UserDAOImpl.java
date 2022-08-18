package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User findByEmail(String email) {
        return entityManager.createQuery("select user from User user where user.email=:email", User.class)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public User getOne(Long id) {
        TypedQuery<User> query = entityManager
                .createQuery("select distinct u from User u JOIN FETCH u.roles where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
