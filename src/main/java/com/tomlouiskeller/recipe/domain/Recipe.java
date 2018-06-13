package com.tomlouiskeller.recipe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Setter
@Getter
@Document
public class Recipe implements Comparable<Recipe> {

    @Id
    private String id;
    private String title;
    private Integer preparationDuration;
    private Integer cookingDuration;
    private Integer servings;
    private String source;
    private String url;
    private Byte[] image;

    // Relations
    private Difficulty difficulty;
    private Instruction instruction;
    private NutritionalInfo nutritionalInfo;
    private final SortedSet<Ingredient> ingredients = new TreeSet<>();

    @DBRef
    private final Set<Category> categories = new TreeSet<>();

    public Recipe() {}

    @Builder
    public Recipe(String id, String title, Integer preparationDuration, Integer cookingDuration, Integer servings, String source, String url, Byte[] image, Difficulty difficulty, Instruction instruction, NutritionalInfo nutritionalInfo) {
        this.id = id;
        this.title = title;
        this.preparationDuration = preparationDuration;
        this.cookingDuration = cookingDuration;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.image = image;
        this.difficulty = difficulty;
        this.instruction = instruction;
        this.nutritionalInfo = nutritionalInfo;
    }

    public Recipe addIngredient(Ingredient ingredient){
        this.getIngredients().add(ingredient);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories.clear();
        if (categories != null) this.categories.addAll(categories);
    }

    @Override
    public int compareTo(Recipe recipe) {
        return LambdaTitleComparator.compare(this, recipe);
    }

    // Comparators
    public static Comparator<Recipe> LambdaIdComparator = Comparator.comparing(Recipe::getId);
    public static Comparator<Recipe> LambdaTitleComparator = Comparator.comparing(Recipe::getTitle);
}
