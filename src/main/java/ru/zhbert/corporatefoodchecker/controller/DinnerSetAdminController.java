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

import java.lang.reflect.Array;
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

        localDates.clear();
        localDates.add(LocalDate.now());

        ArrayList<DinnerSetAdmin> dinnerSetAdminsByDate = (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now());
        if (dinnerSetAdminsByDate.isEmpty()) {
            DinnerSetAdmin dinnerSetAdmin = new DinnerSetAdmin();
            dinnerSetAdmin.setDinnerDate(LocalDate.now());
            dinnerSetAdminRepo.save(dinnerSetAdmin);
        }

        for (int i = 1; i < 15; i++) {
            localDates.add(LocalDate.now().plusDays(i));
            dinnerSetAdminsByDate.clear();
            dinnerSetAdminsByDate =
                    (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now().plusDays(i));
            if (dinnerSetAdminsByDate.isEmpty()) {
                DinnerSetAdmin dinnerSetAdmin = new DinnerSetAdmin();
                dinnerSetAdmin.setDinnerDate(LocalDate.now().plusDays(i));
                dinnerSetAdminRepo.save(dinnerSetAdmin);
            }
        }

        ArrayList<DinnerSetAdmin> dinnerSetAdmin = (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findAll();
        Iterable<Dinner> dinners = dinnerRepo.findAll();

        dinnerSetAdminsByDate =
                (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now());

        model.put("dinners", dinners);
        model.put("dates", localDates);
        model.put("dinnersSets", dinnerSetAdmin);
        model.put("dinnersByDate", dinnerSetAdminsByDate);
        return "dinnersetadmin";
    }
}
