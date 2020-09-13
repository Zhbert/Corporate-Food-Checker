/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.DinnerSetAdmin;
import ru.zhbert.corporatefoodchecker.domain.User;
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

    @GetMapping("/admin/dinners-set")
    public String dinnersSetView(Map<String, Object> model) {

        localDates.clear();
        localDates.add(LocalDate.now());

        ArrayList<DinnerSetAdmin> dinnerSetAdminsByDate =
                (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now());
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

        for (DinnerSetAdmin dinnerSetAdmin1 : dinnerSetAdmin) {
            if (dinnerSetAdmin1.getDinnerDate().isBefore(LocalDate.now())) {
                dinnerSetAdminRepo.delete(dinnerSetAdmin1);
            }
        }

        dinnerSetAdmin = (ArrayList<DinnerSetAdmin>) ((ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findAll());
        dinnerSetAdmin.remove(0);

        Iterable<Dinner> dinners = dinnerRepo.findAll();

        dinnerSetAdminsByDate =
                (ArrayList<DinnerSetAdmin>) dinnerSetAdminRepo.findByDinnerDate(LocalDate.now());

        model.put("dinners", dinners);
        model.put("dates", localDates);
        model.put("dinnersSets", dinnerSetAdmin);
        model.put("dinnersByDate", dinnerSetAdminsByDate);
        return "dinnersetadmin";
    }

    @GetMapping("/admin/dinner-set")
    public String changeDinnerByDate(@AuthenticationPrincipal User user,
                                     @RequestParam String id,
                                     Map<String, Object> model) {
        if (user.isAdmin()) {
            Optional<DinnerSetAdmin> dinnerSetAdminOptional = dinnerSetAdminRepo.findById(Long.valueOf(id));
            if (dinnerSetAdminOptional.isPresent()) {
                DinnerSetAdmin dinnerSetAdmin = dinnerSetAdminOptional.get();
                model.put("dinnerSet", dinnerSetAdmin);
                model.put("date", dinnerSetAdmin.getDinnerDate());
                Iterable<Dinner> dinners = dinnerRepo.findAll();
                model.put("dinners", dinners);
                return "dinnersetchange";
            }
        }
        return "errornotadmin";
    }

    @PostMapping("/admin/dinner-set/change")
    public String setNewDinners(@RequestParam String set_id,
                                @RequestParam String firstDinner,
                                @RequestParam String secondDinner,
                                @RequestParam Map<String, String> form) {
        Optional<DinnerSetAdmin> dinnerSetAdminOptional = dinnerSetAdminRepo.findById(Long.valueOf(set_id));
        if (dinnerSetAdminOptional.isPresent()) {
            DinnerSetAdmin dinnerSetAdmin = dinnerSetAdminOptional.get();
            Dinner dinner = dinnerRepo.findById(Integer.parseInt(firstDinner));
            Dinner dinnerTwo = dinnerRepo.findById(Integer.parseInt(secondDinner));

            dinnerSetAdmin.setDinner(dinner);
            dinnerSetAdmin.setDinnerTwo(dinnerTwo);
            dinnerSetAdminRepo.save(dinnerSetAdmin);
        }


        return "redirect:/admin/dinners-set";
    }
}
