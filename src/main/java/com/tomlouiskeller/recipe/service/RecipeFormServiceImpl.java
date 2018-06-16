package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Ingredient;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class RecipeFormServiceImpl implements RecipeFormService{

    private IngredientService ingredientService;

    public RecipeFormServiceImpl(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public Recipe convert(RecipeForm rF) {
        SortedSet<Ingredient> ingredientSortedSet = new TreeSet<>();

        for (Ingredient recipeIngredient : rF.getRecipeIngredients()) {
            Double amount = recipeIngredient.getAmount();
            String productName = recipeIngredient.getProductName();
            String unitOfMeasurementName = recipeIngredient.getUnitOfMeasurementName();
            Ingredient ingredient = ingredientService.ingredientFactory(amount, unitOfMeasurementName, productName);
            ingredientSortedSet.add(ingredient);
        }

        Recipe recipe = Recipe.builder()
                .id(rF.getRecipeId())
                .title(rF.getRecipeTitle())
                .preparationDuration(rF.getRecipePreparationDuration())
                .cookingDuration(rF.getRecipeCookingDuration())
                .servings(rF.getRecipeServings())
                .source(rF.getRecipeSource())
                .url(rF.getRecipeUrl())
                .image(rF.getRecipeImage()) // TODO: Have to examine this
                .difficulty(rF.getRecipeDifficulty())
                .instruction(rF.getRecipeInstruction())
                .categories(rF.getRecipeCategories())
                .ingredients(ingredientSortedSet)
                .build();

        return recipe;
    }

    @Override
    public RecipeForm convert(Recipe recipe, SortedSet<Category> availableCategories) {
        SortedSet<Ingredient> ingredientSet = recipe.getIngredients();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientSet.forEach(ingredientList::add);

        RecipeForm recipeForm = RecipeForm.builder()
                .recipeId(recipe.getId())
                .recipeTitle(recipe.getTitle())
                .recipePreparationDuration(recipe.getPreparationDuration())
                .recipeCookingDuration(recipe.getCookingDuration())
                .recipeServings(recipe.getServings())
                .recipeSource(recipe.getSource())
                .recipeUrl(recipe.getUrl())
                .recipeImage(recipe.getImage())
                .recipeDifficulty(recipe.getDifficulty())
                .recipeInstruction(recipe.getInstruction())
                .recipeCategories(recipe.getCategories())
                .availableCategories(availableCategories)
                .recipeIngredients(ingredientList)
                .build();

        return recipeForm;
    }
}
