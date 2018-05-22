package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();
    Set<Recipe> findQuickRecipes(Integer maxDuration);
    Recipe save(Recipe recipe);
    Set<Recipe> saveAll(Iterable<Recipe> recipes);

    Recipe findById(Long id);
}
