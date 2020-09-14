/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.domain.UserChoice;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;
import ru.zhbert.corporatefoodchecker.repos.DinnerSetAdminRepo;
import ru.zhbert.corporatefoodchecker.repos.UserChoiceRepo;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserChoiceController {
    @Autowired
    private DinnerSetAdminRepo dinnerSetAdminRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserChoiceRepo userChoiceRepo;

    private List<LocalDate> localDates = new ArrayList<>();
    private ArrayList<DinnerSetAdmin> dinnerSetAdminsByDate;
    private ArrayList<UserChoice> userChoices = new ArrayList<>();

    @GetMapping("/user-choice")
    public String userChoice(@AuthenticationPrincipal User user,
                             Map<String, Object> model) {

        localDates.clear();

        for (int i = 0; i < 15; i++) {
            localDates.add(LocalDate.now().plusDays(i));
            userChoices.clear();
            userChoices = userChoiceRepo.findByDate(LocalDate.now().plusDays(i));
            if (userChoices.isEmpty()) {
                UserChoice userChoice = new UserChoice();
                userChoice.setDate(LocalDate.now().plusDays(i));
                userChoice.setUser(user);
                userChoiceRepo.save(userChoice);
            }
        }

        Iterable<UserChoice> userChoices = userChoiceRepo.findAll();
        Iterable<DinnerSetAdmin> dinnerSetAdmins = dinnerSetAdminRepo.findAll();
        model.put("choices", userChoices);
        model.put("dsa", dinnerSetAdmins);

        return "userChoice";
    }
}
