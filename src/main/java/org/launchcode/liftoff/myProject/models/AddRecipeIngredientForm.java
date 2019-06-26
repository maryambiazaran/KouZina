package org.launchcode.liftoff.myProject.models;

import javax.validation.constraints.NotNull;

public class AddRecipeIngredientForm {

    private Recipe recipe;
    private Iterable<Ingredient> allIngredients;

    @NotNull
    private int recipeId;

    @NotNull
    private int ingredientId;

    @NotNull
    private double quantity;

    public AddRecipeIngredientForm(){}

    public AddRecipeIngredientForm (Recipe recipe, Iterable<Ingredient> allIngredients) {
        this.recipe = recipe;
        this.allIngredients = allIngredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Iterable<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(Iterable<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
