package com.example.springsecurity2.service;


import com.example.springsecurity2.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user, Long[] roles);

    User getUserById(long id);

    User getUserByName(String name);

    void updateUser(User user, Long[] roles);

    void removeUser(long id);

    List<User> getAllUsers();



}
