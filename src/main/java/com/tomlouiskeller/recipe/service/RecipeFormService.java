package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;

import java.util.SortedSet;

// TODO: add RecipeForm createNew(SortedSet<Category> availableCategories) and use it in NewRecipeController
public interface RecipeFormService {

    Recipe convert(RecipeForm recipeForm);
    RecipeForm convert(Recipe recipe, SortedSet<Category> availableCategories);
}
