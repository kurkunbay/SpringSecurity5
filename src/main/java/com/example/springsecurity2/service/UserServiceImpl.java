package com.example.springsecurity2.service;


import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.DAO.UserDAO;
import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;



    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDao) {
        this.userDAO = userDAO;
        this.roleDAO = roleDao;

    }
    @PostConstruct
    public void addRolesIfNotExist() {
        if ((roleDAO.findRoleById(1L) == null)
                || (roleDAO.findRoleById(2L) == null)) {
            roleDAO.addRoleAdmin();
            roleDAO.addRoleUser();
        }
        if ((roleDAO.findRoleById(1L) != null)
                || (roleDAO.findRoleById(2L) != null)) {
            System.err.println("Roles exist");
        }
    }
    @Transactional
    @Override
    public User addUser(User user, Long[] roles) {
        User userFindDB = userDAO.getUserByName(user.getUsername());

        if (userFindDB != null) {
            return userFindDB;
        }

        Set<Role> role = roleDAO.findRolesSetById(roles);

        user.setRoles(role);
        user.setPassword(user.getPassword());
        userDAO.addUser(user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void updateUser(User user, Long[] roles) {
        User modifyUser = userDAO.getUserById(user.getId());
        modifyUser.setEmail(user.getUsername());

        Set<Role> roleSet = roleDAO.findRolesSetById(roles);
        modifyUser.setRoles(roleSet);

        if (!user.getPassword().equals(modifyUser.getPassword())) {
            modifyUser.setPassword(user.getPassword());
        }
        userDAO.updateUser(modifyUser);
    }

    @Transactional
    @Override
    public void removeUser(long id) {
        userDAO.removeUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
