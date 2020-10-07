/*
 * Copyright (c) 2020. Created by Zhbert.
 */

package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.Message;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DinnerControler {
    @Autowired
    private DinnerRepo dinnerRepo;

    @GetMapping("/admin/dinners")
    public String dinners(Model model) {
        Iterable<Dinner> dinners = dinnerRepo.findAll();
        model.addAttribute("dinners", dinners);
        return "dinners";
    }

    @PostMapping("/admin/dinners")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Dinner dinner,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", dinner);
        } else {
            dinnerRepo.save(dinner);
            model.addAttribute("message", null);
        }
        Iterable<Dinner> dinners = dinnerRepo.findAll();
        model.addAttribute("dinners", dinners);
        return "dinners";
    }

    @GetMapping("/admin/dinner-change")
    public String changeDinner(@AuthenticationPrincipal User user,
                               @RequestParam String id,
                               Map<String, Object> model) {
        Dinner dinner = dinnerRepo.findById(Integer.parseInt(id));
        model.put("dinner", dinner);
        return "dinnerchange";
    }

    @PostMapping("/admin/dinner-change")
    public String saveDinnerChange(@AuthenticationPrincipal User user,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("id") String id,
                                   Map<String, Object> model) {
        Dinner dinner = dinnerRepo.findById(Integer.parseInt(id));
        dinner.setDescription(description);
        dinner.setName(name);
        dinnerRepo.save(dinner);
        return "redirect:/admin/dinners";
    }

    @GetMapping("/admin/dinner-delete")
    public String deleteDinner(@AuthenticationPrincipal User user,
                               @RequestParam String id,
                               Map<String, Object> model) {
        Dinner dinner = dinnerRepo.findById(Integer.parseInt(id));
        model.put("dinner", dinner);
        return "dinnerdelete";
    }

    @PostMapping("/admin/dinner-delete/{id}")
    public String deleteDinner(@AuthenticationPrincipal User user,
                               @PathVariable("id") String id) {
        Dinner dinner = dinnerRepo.findById(Integer.parseInt(id));

        dinnerRepo.delete(dinner);
        return "redirect:/admin/dinners";
    }
}
