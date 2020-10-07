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
import ru.zhbert.corporatefoodchecker.service.UserService;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
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
            @RequestParam String password,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        userService.saveUser(user, username, password, form);

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
        userService.addUser(name, password, model);
        return "redirect:/user";

    }
}
