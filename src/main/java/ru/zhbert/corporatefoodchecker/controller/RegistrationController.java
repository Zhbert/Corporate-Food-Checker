/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zhbert.corporatefoodchecker.domain.Role;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFormDB = userRepo.findByUsername(user.getUsername());

        if (userFormDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
