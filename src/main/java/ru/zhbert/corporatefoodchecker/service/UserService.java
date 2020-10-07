/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zhbert.corporatefoodchecker.domain.Role;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }


    public void saveUser(User user, String username, String password, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userRepo.save(user);
    }


    public Boolean addUser(String name, String password, Map<String, Object> model) {
        User userFormDB = userRepo.findByUsername(name);

        if (userFormDB != null) {
            model.put("message", "User exists!");
            return false;
        }

        User user = new User();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);

        return true;
    }
}
