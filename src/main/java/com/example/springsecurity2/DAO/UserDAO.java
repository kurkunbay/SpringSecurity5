package com.example.springsecurity2.DAO;


import com.example.springsecurity2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO  {
    User findById(Long id);

     List<User> findAll();

    void deleteById(Long id);

    User findByEmail(String email);

    User getOne(Long id);

    User save(User user);
}
