package com.tomlouiskeller.recipe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(exclude="ingredients")
@ToString(exclude = "ingredients")
@Entity
public class Recipe implements Comparable<Recipe> {

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
    private byte[] image;

    // Relations
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Instruction instruction;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private NutritionalInfo nutritionalInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    @SortComparator(value = Ingredient.class)
    private SortedSet<Ingredient> ingredients = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<Rating> rating = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY) // Lazy because if we set it to eager and get all recipes of a category, we load the whole database
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new TreeSet<>();

    public Recipe() {
    }

    @Builder
    public Recipe(Long id, String title, Integer preparationDuration, Integer cookingDuration, Integer servings, String source, String url, byte[] image, Difficulty difficulty, Instruction instruction, NutritionalInfo nutritionalInfo, SortedSet<Ingredient> ingredients, List<Rating> rating, Set<Category> categories) {
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
        this.ingredients = ingredients;
        this.rating = rating;
        this.categories = categories;
    }




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

    @Override
    public int compareTo(Recipe recipe) {
        return LambdaTitleComparator.compare(this, recipe);
    }

    // Comparators

    public static Comparator<Recipe> IdComparator = (Recipe r1, Recipe r2) -> {
        // This is so stupid
        if (r1 == null && r2 == null){
            return 0;
        } else if (r1 == null){
            return 1;
        } else if (r2 == null){
            return -1;
        } else if (r1.getId() == null && r2.getId() == null){
            return 0;
        } else if (r1.getId() == null){
            return 1;
        } else if (r2.getId() == null){
            return -1;
        } else {
            return (int) (r1.getId() - r2.getId());
        }
    };

    public static Comparator<Recipe> LambdaIdComparator = Comparator.comparing(Recipe::getId);

    public static Comparator<Recipe> TitleComparator = (Recipe r1, Recipe r2) -> {
        // This is so stupid
        if (r1 == null && r2 == null){
            return 0;
        } else if (r1 == null){
            return 1;
        } else if (r2 == null){
            return -1;
        } else if (r1.getTitle() == null && r2.getTitle() == null){
            return 0;
        } else if (r1.getTitle() == null){
            return 1;
        } else if (r2.getTitle() == null){
            return -1;
        } else {
            return r1.getTitle().compareTo(r2.getTitle());
        }
    };

    public static Comparator<Recipe> LambdaTitleComparator = Comparator.comparing(Recipe::getTitle);
}
