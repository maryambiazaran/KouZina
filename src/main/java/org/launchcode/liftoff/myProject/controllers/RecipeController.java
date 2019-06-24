package org.launchcode.liftoff.myProject.controllers;

import org.launchcode.liftoff.myProject.models.data.RecipeCategoryDao;
import org.launchcode.liftoff.myProject.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private RecipeCategoryDao recipeCategoryDao;


    @RequestMapping("")
    public String displayRecipe(Model model) {
        model.addAttribute("title", "Cookbook");
        model.addAttribute("recipes", recipeDao.findAll());
        return "recipes/index";

    }


}
