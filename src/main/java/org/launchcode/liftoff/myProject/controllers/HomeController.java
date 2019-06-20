package org.launchcode.liftoff.myProject.controllers;

import org.launchcode.liftoff.myProject.models.data.IngredientCategoryDao;
import org.launchcode.liftoff.myProject.models.data.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "about")
    public String recipeView(Model model) {
        model.addAttribute("title", "About me");
        model.addAttribute("testText", "I'll put more stuff here later!");

        return "index";
    }

}
