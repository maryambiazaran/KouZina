package org.launchcode.liftoff.myProject.controllers;

import org.launchcode.liftoff.myProject.models.Recipe;
import org.launchcode.liftoff.myProject.models.RecipeCategory;
import org.launchcode.liftoff.myProject.models.data.RecipeCategoryDao;
import org.launchcode.liftoff.myProject.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
        model.addAttribute("categories", recipeCategoryDao.findAll());
        return "recipes/index";
    }

    @RequestMapping(value="category")
    public String displayRecipeCategories(Model model) {
        model.addAttribute("title", "Recipe Categories");
        model.addAttribute("categories", recipeCategoryDao.findAll());
        return "recipes/recipeCategory";

    }

    @RequestMapping(value="category/add")
    public String displayAddRecipeCategoryForm(Model model) {
        model.addAttribute("title", "New Recipe Category");
        model.addAttribute(new RecipeCategory());
        return "recipes/addRecipeCategory";

    }

    @RequestMapping(value="category/add",method=RequestMethod.POST)
    public String processAddRecipeCategoryForm(Model model,
                                               @Valid RecipeCategory recipeCategory,
                                               Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Recipe Category");
            return "recipes/addRecipeCategory";
        }
        recipeCategoryDao.save(recipeCategory);
        return "redirect:";

    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "New Recipe");
        model.addAttribute("categories",recipeCategoryDao.findAll());
        model.addAttribute(new Recipe());
        return "recipes/addRecipe";
    }

    @RequestMapping(value ="add", method = RequestMethod.POST)
    public String processAddRecipeForm(Model model,
                                       @Valid Recipe newRecipe,
                                       Errors errors,
                                       int categoryId) {
        if (errors.hasErrors()){
            model.addAttribute("title", "New Recipe");
            model.addAttribute("categories",recipeCategoryDao.findAll());
            return "recipes/addRecipe";
        }
        RecipeCategory recipeCategory = recipeCategoryDao.findOne(categoryId);
        newRecipe.setCategory(recipeCategory);
        recipeDao.save(newRecipe);
        return "redirect:";
    }


}
