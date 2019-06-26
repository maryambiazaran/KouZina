package org.launchcode.liftoff.myProject.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  //  @Id
    @ManyToOne
    @JoinColumn
    private Ingredient ingredient;

  //  @Id
    @ManyToOne
    @JoinColumn
    private Recipe recipe;

    private double quantity;

    // =========== CONSTRUCTOR =============

    public RecipeIngredient() {}

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, double quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    // =========== GETTER & SETTER =============

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // =========== EQUALS & HASHCODE =============

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return  ingredient.equals(that.ingredient) &&
                recipe.equals(that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, recipe);
    }
}

