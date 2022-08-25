package com.example.springsecurity2.DAO;


import com.example.springsecurity2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    List<User> getAllUsers();

    void updateUser(User user);

    void removeUser(long id);
}
