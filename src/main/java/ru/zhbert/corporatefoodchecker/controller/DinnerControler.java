package ru.zhbert.corporatefoodchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhbert.corporatefoodchecker.domain.Dinner;
import ru.zhbert.corporatefoodchecker.domain.Message;
import ru.zhbert.corporatefoodchecker.domain.User;
import ru.zhbert.corporatefoodchecker.repos.DinnerRepo;

import java.util.Map;

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
                      @RequestParam String name,
                      @RequestParam String description,
                      Map<String, Object> model) {
        Dinner dinner = new Dinner(name, description);
        dinnerRepo.save(dinner);

        Iterable<Dinner> dinners = dinnerRepo.findAll();
        model.put("dinners", dinners);
        return "dinners";
    }
}
