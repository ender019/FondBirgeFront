package com.finance.birge.controllers;

import com.finance.birge.services.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;

@Slf4j
@Controller
public class MainController
{
    @Autowired
    private RestService restService;

    @GetMapping("/")
    public String home(Model model) {
        log.info("Home page");
        model.addAttribute("title", "Home");

        return "home";
    }

    @GetMapping("/birge/{page}")
    public String birge(@PathVariable(value = "page") String path, Model model) {
        log.info("Birge page with page {}", path);
        LinkedHashMap<String,Float> res = restService.getCourses(path);
        model.addAttribute("title", path+" cost");
        model.addAttribute("path", path);
        model.addAttribute("data_kol", res.size());
        model.addAttribute("data", res);

        return "birge";
    }

    @GetMapping("/birge/get/{page}")
    public String get_allcost(@PathVariable(value = "page") String path, Model model) {
        log.info("Cost page with page {}", path);
        LinkedHashMap<String,Float> res = restService.getCourses(path);
        model.addAttribute("data_kol", res.size());
        model.addAttribute("data", res);

        return "blocks/board";
    }
}