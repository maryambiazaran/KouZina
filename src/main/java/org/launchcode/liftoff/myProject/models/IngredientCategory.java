package org.launchcode.liftoff.myProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IngredientCategory {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Category name can not be empty")
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Ingredient> ingredients = new ArrayList<>();


    public IngredientCategory() {}

    public IngredientCategory(String name) {
        this();
        this.name = name;
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
}
