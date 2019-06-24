package org.launchcode.liftoff.myProject.controllers;

import org.launchcode.liftoff.myProject.models.Ingredient;
import org.launchcode.liftoff.myProject.models.IngredientCategory;
import org.launchcode.liftoff.myProject.models.data.IngredientCategoryDao;
import org.launchcode.liftoff.myProject.models.data.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientCategoryDao ingredientCategoryDao;

    @Autowired
    private IngredientDao ingredientDao;

    @RequestMapping(value="")
    public String displayIngredients(Model model) {
        model.addAttribute("title", "Ingredients");
        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("categories", ingredientCategoryDao.findAll());

        return "ingredients/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddIngredientForm(Model model) {
        model.addAttribute("title", "Add Ingredient");
        model.addAttribute(new Ingredient());
        model.addAttribute("categories", ingredientCategoryDao.findAll());

        return "ingredients/addingredient";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddIngredientForm(@ModelAttribute  @Valid Ingredient newIngredient,
                                           Errors errors,
                                           @RequestParam int categoryId,
                                           Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredient");
            return "ingredients/add";
        }
        IngredientCategory ingcat = ingredientCategoryDao.findOne(categoryId);
        newIngredient.setCategory(ingcat);
        ingredientDao.save(newIngredient);
        return "redirect:";

    }

    @RequestMapping(value="category")
    public String displayCategories(Model model) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", ingredientCategoryDao.findAll());

        return "ingredients/ingredientCategories";
    }


    @RequestMapping(value="category/add", method = RequestMethod.GET)
    public String displayAddIngredientCategoryForm(Model model) {
        model.addAttribute("title", "New Category");
        model.addAttribute(new IngredientCategory());

        return "ingredients/addcategory";
    }

    @RequestMapping(value="category/add", method = RequestMethod.POST)
    public String processAddIngredientCategoryForm(Model model,
                                                   @ModelAttribute @Valid IngredientCategory ingredientCategory,
                                                   Errors errors) {
        if (errors.hasErrors()) {
            return "category/add";
        } else {
            ingredientCategoryDao.save(ingredientCategory);
            return "redirect:";
        }

    }


}
