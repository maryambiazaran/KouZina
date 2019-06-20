package org.launchcode.liftoff.myProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to my App!");
        return "index";
    }

    @RequestMapping(value = "recipes")
    public String recipeView(Model model) {
        model.addAttribute("title", "My Recipe Book");
        return "index";
    }

}
