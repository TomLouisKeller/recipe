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
    private String instruction;


    // Relations
    private Difficulty difficulty;

    private final SortedSet<Ingredient> ingredients = new TreeSet<>();

    @DBRef
    private final Set<Category> categories = new TreeSet<>(); // TODO: Refactor to SortedSet

    public Recipe() {}

    @Builder
    public Recipe(String id, String title, Integer preparationDuration, Integer cookingDuration, Integer servings, String source, String url, Byte[] image, String instruction, Difficulty difficulty, SortedSet<Ingredient> ingredients, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.preparationDuration = preparationDuration;
        this.cookingDuration = cookingDuration;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.image = image;
        this.instruction = instruction;
        this.difficulty = difficulty;

        if (ingredients != null)
            this.setIngredients(ingredients);
        if (categories != null)
            this.setCategories(categories);
    }

    public Recipe addIngredient(Ingredient ingredient){
        this.getIngredients().add(ingredient);
        return this;
    }

    public void setIngredients(SortedSet<Ingredient> ingredients) {
        this.ingredients.clear();
        if (ingredients != null) this.ingredients.addAll(ingredients);
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
