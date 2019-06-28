package org.launchcode.liftoff.myProject.controllers;

import org.launchcode.liftoff.myProject.models.*;
import org.launchcode.liftoff.myProject.models.data.IngredientDao;
import org.launchcode.liftoff.myProject.models.data.RecipeCategoryDao;
import org.launchcode.liftoff.myProject.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private RecipeCategoryDao recipeCategoryDao;

    @Autowired
    private IngredientDao ingredientDao;


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

    @RequestMapping(value="view/{recipeId}", method=RequestMethod.GET)
    public String viewRecipe(Model model, @PathVariable int recipeId) {
        Recipe theRecipe = recipeDao.findOne(recipeId);
        model.addAttribute("recipe", theRecipe);
        model.addAttribute("title", theRecipe.getName());
        model.addAttribute("categories", recipeCategoryDao.findAll());
        return "recipes/view";
    }

    @RequestMapping(value="view", method=RequestMethod.POST)
    public String processViewRecipe(Model model,
                                    String name,
                                    int recipeId,
                                    int categoryId,
                                    int servingSize,
                                    String description) {
        Recipe theRecipe = recipeDao.findOne(recipeId);
        theRecipe.setName(name);
        theRecipe.setDescription(description);
        theRecipe.setServingSize(servingSize);
        theRecipe.setCategory(recipeCategoryDao.findOne(categoryId));
        recipeDao.save(theRecipe);
        return "redirect:";
    }

    @RequestMapping(value="addingredient/{recipeId}", method=RequestMethod.GET)
    public String viewAddRecipeIngredientForm(Model model,
                                    @PathVariable int recipeId) {
        Recipe theRecipe = recipeDao.findOne(recipeId);
        Iterable<Ingredient> ingredients =  ingredientDao.findAll();
        model.addAttribute("form", new AddRecipeIngredientForm(theRecipe,ingredients));
        model.addAttribute("title", theRecipe.getName());
        return "recipes/addIngredient";
    }

    @RequestMapping(value="addingredient", method=RequestMethod.POST)
    //@ResponseBody
    public String processAddRecipeIngredientForm(Model model,
                                                 @Valid AddRecipeIngredientForm form,
                                                 Errors errors ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredients");
            return "recipes/view";
        }

        Recipe recipe = recipeDao.findOne(form.getRecipeId());
        Ingredient ingredient = ingredientDao.findOne(form.getIngredientId());
        Double quantity = form.getQuantity();
        RecipeIngredient newrecipeIngredient = new RecipeIngredient(recipe, ingredient, quantity);
        recipe.getRecipeIngredients().add(newrecipeIngredient);
        recipeDao.save(recipe);
        return "redirect:view/"+recipe.getId();
    }

    @RequestMapping(value="edit/{recipeId}/{ingredientId}")
    public String displayRecipeIngredientEditForm(Model model,
                                                  @PathVariable int recipeId,
                                                  @PathVariable int ingredientId) {
        Recipe theRecipe = recipeDao.findOne(recipeId);
        Ingredient theIngredient = ingredientDao.findOne(ingredientId);
        double q = theRecipe.findRecipeIngredientByIngredient(ingredientId).getQuantity();
        AddRecipeIngredientForm form = new AddRecipeIngredientForm(theRecipe);
        form.setRecipeId(recipeId);
        form.setIngredientId(ingredientId);
        form.setQuantity(q);
        model.addAttribute("form", form);
        model.addAttribute("ingredient", theIngredient);
        return "recipes/editIngredient";

    }

    @RequestMapping(value="/editingredient", method=RequestMethod.POST)
    public String processRecipeIngredientEditForm(Model model,
                                                  @Valid AddRecipeIngredientForm form,
                                                  Errors errors) {
        Recipe theRecipe = recipeDao.findOne(form.getRecipeId());
        RecipeIngredient recIng = theRecipe.findRecipeIngredientByIngredient(form.getIngredientId());
        recIng.setQuantity(form.getQuantity());
        recipeDao.save(theRecipe);
        return "redirect:view/"+theRecipe.getId();
    }

}
