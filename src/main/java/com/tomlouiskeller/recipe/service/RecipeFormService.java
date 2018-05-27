package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;

public interface RecipeFormService {

    Recipe convert(RecipeForm recipeForm);
}
