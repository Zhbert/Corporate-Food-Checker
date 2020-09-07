/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;
import ru.zhbert.corporatefoodchecker.repos.DinnerSetAdminRepo;

import java.time.LocalDate;
import java.util.*;

@Controller
public class DinnerSetAdminController {
    @Autowired
    private DinnerSetAdminRepo dinnerSetAdminRepo;
    @Autowired
    private DinnerRepo dinnerRepo;

    private List<LocalDate> localDates = new ArrayList<>();

    @GetMapping("/dinners-set")
    public String dinnersSetView(Map<String, Object> model) {

        LocalDate localDate = LocalDate.now();
        localDates.clear();
        localDates.add(localDate);

        for (int i=1; i<15; i++) {
            localDate = LocalDate.now().plusDays(i);
            localDates.add(localDate);
        }

        Iterable<DinnerSetAdmin> dinnerSetAdmin = dinnerSetAdminRepo.findAll();
        Iterable<DinnerSetAdmin> dinnerSetAdminsByDate = (Iterable<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now());
        Iterable<Dinner> dinners = dinnerRepo.findAll();

        model.put("dinners", dinners);
        model.put("dates", localDates);
        model.put("dinnersSets", dinnerSetAdmin);
        model.put("dinnersByDate", dinnerSetAdminsByDate);
        return "dinnersetadmin";
    }
}
