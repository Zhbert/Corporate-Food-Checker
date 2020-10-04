/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.domain.UserChoice;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;
import ru.zhbert.corporatefoodchecker.repos.DinnerSetAdminRepo;
import ru.zhbert.corporatefoodchecker.repos.UserChoiceRepo;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class UserChoiceController {
    @Autowired
    private DinnerSetAdminRepo dinnerSetAdminRepo;

    @Autowired
    private DinnerRepo dinnerRepo;

    @Autowired
    private UserChoiceRepo userChoiceRepo;

    private List<LocalDate> localDates = new ArrayList<>();
    private ArrayList<UserChoice> userChoices = new ArrayList<>();
    private ArrayList<UserChoice> userChoicesByUser = new ArrayList<>();
    private Boolean isAfter;

    @GetMapping("/user-choice")
    public String userChoice(@AuthenticationPrincipal User user,
                             Map<String, Object> model) throws ParseException {

        localDates.clear();

        for (int i = 0; i < 15; i++) {
            localDates.add(LocalDate.now().plusDays(i));
            userChoices.clear();
            userChoices = userChoiceRepo.findByDateAndUser(LocalDate.now().plusDays(i), user);
            if (userChoices.isEmpty()) {
                UserChoice userChoice = new UserChoice();
                userChoice.setDate(LocalDate.now().plusDays(i));
                userChoice.setUser(user);
                userChoiceRepo.save(userChoice);
            }
        }

        ArrayList<UserChoice> userChoicesDel = (ArrayList<UserChoice>) userChoiceRepo.findAll();
        for (UserChoice choice1 : userChoicesDel) {
            if (choice1.getDate().isBefore(LocalDate.now())) {
                userChoiceRepo.delete(choice1);
            }
        }

        userChoicesByUser.clear();
        for (int i = 0; i < 15; i++) {
            userChoices.clear();
            userChoices = userChoiceRepo.findByDateAndUser(LocalDate.now().plusDays(i), user);
            if (!userChoices.isEmpty()) {
                userChoicesByUser.add(userChoices.get(0));
            }
        }
        Iterable<DinnerSetAdmin> dinnerSetAdmins = dinnerSetAdminRepo.findAll();
        model.put("choices", userChoicesByUser);
        model.put("dsa", dinnerSetAdmins);

        //check time (10:00)
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);

        if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("10:00"))) {
            isAfter = true;
        } else {
            isAfter = false;
        }
        model.put("isAfter", isAfter);

        return "userChoice";
    }

    @GetMapping("/user-choice/set")
    public String setUserChoice(Map<String, Object> model,
                                @RequestParam String id) {
        Optional<UserChoice> userChoice = userChoiceRepo.findById(Long.valueOf(id));
        UserChoice choice = new UserChoice();
        if (userChoice.isPresent()) {
            choice = userChoice.get();
            model.put("choice", choice);
        }

        Iterable<DinnerSetAdmin> dinnerSetAdmin = dinnerSetAdminRepo.findByDinnerDate(choice.getDate());
        model.put("dsa", dinnerSetAdmin);

        return "userChoiceSet";
    }

    @PostMapping("/user-choice/set")
    public String setUserChoicePost(Map<String, Object> model,
                                    @AuthenticationPrincipal User user,
                                    @RequestParam String choiceId,
                                    @RequestParam String dinner_id) {

        Optional<UserChoice> choice = userChoiceRepo.findById(Long.valueOf(choiceId));
        if (choice.isPresent()) {
            UserChoice userChoice = choice.get();
            Dinner dinner = dinnerRepo.findById(Integer.parseInt(dinner_id));
            userChoice.setDinner(dinner);
            userChoiceRepo.save(userChoice);
        }

        return "redirect:/user-choice";
    }
}
