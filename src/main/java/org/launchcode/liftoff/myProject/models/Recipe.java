package org.launchcode.liftoff.myProject.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(name = "name")
    @Size(min=3, message = "Minimum characters: 3")
    private String name;

    private String description;

    @ManyToOne
    private RecipeCategory category;

    private int servingSize;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients;

    public Recipe(){}

    public Recipe(String name, RecipeIngredient... recipeIngredients) {
        this.name = name;
        for (RecipeIngredient recipeIngredient: recipeIngredients) recipeIngredient.setRecipe(this);
        this.recipeIngredients = Stream.of(recipeIngredients).collect(Collectors.toSet());

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
