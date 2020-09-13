/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;
import ru.zhbert.corporatefoodchecker.repos.DinnerSetAdminRepo;
import ru.zhbert.corporatefoodchecker.repos.UserRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class UserChoiceController {
    @Autowired
    private DinnerRepo dinnerRepo;

    @Autowired
    private DinnerSetAdminRepo dinnerSetAdminRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user-choice")
    public String userChoice(Map<String, Object> model) {

        return "greeting";
    }
}
