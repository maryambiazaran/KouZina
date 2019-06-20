package org.launchcode.liftoff.myProject.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class RecipeIngredient implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Ingredient ingredient;

    @Id
    @ManyToOne
    @JoinColumn
    private Recipe recipe;

    private double quantity;

    public RecipeIngredient(Ingredient ingredient, double quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
