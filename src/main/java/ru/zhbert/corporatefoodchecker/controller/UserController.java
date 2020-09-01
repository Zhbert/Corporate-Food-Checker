/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhbert.corporatefoodchecker.domain.Role;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEdiForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        user.setUsername(username);
        userRepo.save(user);

        return "redirect:/user";
    }

    @GetMapping("/user-delete/{user}")
    public String delUser(
            @PathVariable("user") String username,
            Model model
    ) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        return "userdelete";
    }

    @PostMapping("/user-delete/{user}")
    public String confirmDelUser(
            @PathVariable("user") String username
    ) {
        User user = userRepo.findByUsername(username);
        userRepo.delete(user);
        return "redirect:/user";
    }

    @PostMapping("/register")
    public String addUser(
            @RequestParam String name,
            @RequestParam String password,
            Map<String, Object> model) {
        User userFormDB = userRepo.findByUsername(name);

        if (userFormDB != null) {
            model.put("message", "User exists!");
            return "/user";
        }

        User user = new User();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setUsername(name);
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/user";
    }
}
