package com.tomlouiskeller.recipe.form;

import com.tomlouiskeller.recipe.domain.Difficulty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

// TODO: Create tests for all the validation

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeForm {


    // From domain.Recipe
    private Long recipeId;

    @NotBlank
    @Size(min = 3, max = 255)
    private String recipeTitle;

    @Positive
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


    // From domain.Instruction
    @NotBlank
    private String instructionText;


    // From domain.Instruction
    @NotBlank
    private String nutritionalInfoText;

    // TODO: Do these afterwards.
    // private Set<Ingredient> ingredients = new HashSet<>();
    // private Set<Category> categories = new HashSet<>();

}
