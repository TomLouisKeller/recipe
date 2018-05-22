package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude="ingredients")
@ToString(exclude = "ingredients")
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer preparationDuration;
    private Integer cookingDuration;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private Byte[] image;

    // Relations
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Instruction instruction;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private NutritionalInfo nutritionalInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<Rating> rating = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY) // Lazy because if we set it to eager and get all recipes of a category, we load the whole database
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
        return this;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
        if (instruction == null) return;
        instruction.setRecipe(this);
    }

    public void setNutritionalInfo(NutritionalInfo nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
        if (nutritionalInfo == null) return;
        nutritionalInfo.setRecipe(this);
    }
}
