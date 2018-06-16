package com.tomlouiskeller.recipe.form;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Difficulty;
import com.tomlouiskeller.recipe.domain.Ingredient;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.*;

// TODO: Remove the "recipe" prefix
@Data
public class RecipeForm {

    // From domain.Recipe
    private String recipeId;

    @Size(min = 3, max = 255)
    private String recipeTitle;

    @PositiveOrZero
    private Integer recipePreparationDuration;

    @PositiveOrZero
    private Integer recipeCookingDuration;

    @Positive
    private Integer recipeServings;

    @Size(max = 255)
    private String recipeSource;

    @Size(max = 255)
    private String recipeUrl;

    // TODO: Find validation so byte is not to big! Maybe we just block the upload
    private Byte[] recipeImage;

    private Difficulty recipeDifficulty;

    @NotBlank
    private String recipeInstruction;

    private List<Ingredient> recipeIngredients = new ArrayList<>();

    private SortedSet<Category> availableCategories;
    private Set<Category> recipeCategories = new HashSet<>();

    public RecipeForm() {
    }

    // This constructor is only used through the builder. Annotating the class with @Builder doesn't work
    // Otherwise NoArgsConstructor is private
    @Builder
    public RecipeForm(String recipeId, @Size(min = 3, max = 255) String recipeTitle, @PositiveOrZero Integer recipePreparationDuration, @PositiveOrZero Integer recipeCookingDuration, @Positive Integer recipeServings, @Size(max = 255) String recipeSource, @Size(max = 255) String recipeUrl, Byte[] recipeImage, Difficulty recipeDifficulty, @NotBlank String recipeInstruction, List<Ingredient> recipeIngredients, SortedSet<Category> availableCategories, Set<Category> recipeCategories) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipePreparationDuration = recipePreparationDuration;
        this.recipeCookingDuration = recipeCookingDuration;
        this.recipeServings = recipeServings;
        this.recipeSource = recipeSource;
        this.recipeUrl = recipeUrl;
        this.recipeImage = recipeImage;
        this.recipeDifficulty = recipeDifficulty;
        this.recipeInstruction = recipeInstruction;
        this.recipeIngredients = recipeIngredients;
        this.availableCategories = availableCategories;
        this.recipeCategories = recipeCategories;
    }
}
