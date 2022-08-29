package com.example.springsecurity2.controller;


import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import com.example.springsecurity2.service.RoleService;
import com.example.springsecurity2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "list";
    }




    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user")  User userForm,
                             @RequestParam(required = false, name = "roles") Long[] rolesId) {
        userService.addUser(userForm, rolesId);
        return "redirect:/admin";
    }

    @GetMapping(value = "/info")
    public String addNewUser(@ModelAttribute("user") User user,
                             Model model) {
        model.addAttribute("userRole", roleService.findRoleByName("ROLE_USER"));
        model.addAttribute("adminRole", roleService.findRoleByName("ROLE_ADMIN"));
        return "info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String[] roles) {
        userService.addUserWithRole(user, roles);
        return "redirect:/admin/";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roles =  roleService.getAllRoles();
        model.addAttribute("getUserById", user);
        model.addAttribute("listRoles", roles );
        return "/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user")  User user,
                             @RequestParam(required = false, name = "roles") Long[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }
}