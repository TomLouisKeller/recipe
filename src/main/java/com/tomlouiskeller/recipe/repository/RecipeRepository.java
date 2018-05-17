package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}