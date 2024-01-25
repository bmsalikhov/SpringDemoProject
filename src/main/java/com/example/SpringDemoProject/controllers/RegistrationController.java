package com.example.SpringDemoProject.controllers;

import com.example.SpringDemoProject.models.Role;
import com.example.SpringDemoProject.models.User;
import com.example.SpringDemoProject.repositories.UserRepository;
import com.example.SpringDemoProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        System.out.println(user.getUsername());
        User userFromDb = userService.findByUsernameEquals(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "user exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);

        return "redirect:/login";
    }
}
