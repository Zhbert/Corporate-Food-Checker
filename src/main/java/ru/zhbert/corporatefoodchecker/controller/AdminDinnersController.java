/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zhbert.corporatefoodchecker.domain.AdminCounter;
import ru.zhbert.corporatefoodchecker.domain.UserChoice;
import ru.zhbert.corporatefoodchecker.repos.UserChoiceRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminDinnersController {
    @Autowired
    private UserChoiceRepo userChoiceRepo;

    private ArrayList<UserChoice> userChoices = new ArrayList<>();
    private List<LocalDate> localDates = new ArrayList<>();
    private HashMap<String, Integer> counters = new HashMap<>();
    private ArrayList<AdminCounter> adminCounters = new ArrayList<>();

    @GetMapping("/admin/view-choices")
    public String adminChoicesView(Map<String, Object> model) {
        adminCounters.clear();
        for (int i = 0; i < 15; i++) {
            userChoices.clear();
            counters.clear();
            userChoices = userChoiceRepo.findByDate(LocalDate.now().plusDays(i));
            for (UserChoice choice : userChoices) {
                if (choice.isExists()) {
                    if (!counters.containsKey(choice.getDinner().getName())) {
                        counters.put(choice.getDinner().getName(), 1);
                    } else {
                        counters.put(choice.getDinner().getName(), counters.get(choice.getDinner().getName()) + 1);
                    }
                }
            }

            AdminCounter adminCounter = new AdminCounter();
            adminCounter.setDate(LocalDate.now().plusDays(i));

            int y = 1;
            for (Map.Entry<String, Integer> entry : counters.entrySet()) {
                if (y == 1) {
                    adminCounter.setDinnerOne(entry.getKey());
                    adminCounter.setDinOneCount(entry.getValue());
                } else {
                    adminCounter.setDinnerTwo(entry.getKey());
                    adminCounter.setDinTwoCount(entry.getValue());
                    break;
                }
                y++;
            }

            adminCounters.add(adminCounter);
        }

        model.put("counters", adminCounters);

        return "viewChoices";
    }
}
