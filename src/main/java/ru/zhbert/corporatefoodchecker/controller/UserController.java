/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhbert.corporatefoodchecker.domain.Role;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;
import ru.zhbert.corporatefoodchecker.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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
            @RequestParam("userId") String userId,
            @Valid User user,
            BindingResult bindingResult,
            @RequestParam Map<String, String> form,
            Model model) {

        Optional<User> userDB = userRepo.findById(Long.valueOf(userId));

        if (userDB.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
                model.addAttribute("error", user);
                model.addAttribute("user", userDB.get());
                model.addAttribute("roles", Role.values());
                return "userEdit";
            } else {
                userService.saveUser(userDB.get(), user.getUsername(), user.getPassword(), form);
                model.addAttribute("error", null);
                model.addAttribute("user", userDB.get());
                model.addAttribute("roles", Role.values());
            }
        }
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
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("error", user);
        } else {
            userService.addUser(user.getUsername(), user.getPassword(), model);
            model.addAttribute("error", null);
        }
        model.addAttribute("users", userService.findAll());
        return "userList";

    }
}
