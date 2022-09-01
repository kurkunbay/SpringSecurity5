package com.example.springsecurity2.service;



import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.DAO.UserDAO;
import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,  RoleDAO roleDAO) {
        this.userDAO = userDAO;

        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User saveUser(User user, String[] roles) {
        if (user.getId() != null) {
            User oldUser = getUserById(user.getId());
            if (user.getPassword().equals("") || user.getPassword() == null ) {
                user.setPassword(oldUser.getPassword());
                System.out.println("Пароль не изменился");
            } else {
                user.setPassword(user.getPassword());
                System.out.println("Пароль изменился");
            }
        } else {
            user.setPassword(user.getPassword());
        }
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleDAO.getByRoleName(roleName));
        }
        user.setRoles(roleSet);
        userDAO.saveUser(user);
        return user;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(email);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User is unknown");
        }
        return user;
    }
}