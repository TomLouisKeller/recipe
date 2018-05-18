package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// TODO: Get Ingredients sorted by id or a separate field
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByTitle(String title);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) <= ?1")
    Iterable<Recipe> findByPreparationDurationPlusCookingDurationIsLessThanEqual(Integer duration);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) >= ?1")
    Iterable<Recipe> findByPreparationDurationPlusCookingDurationIsGreaterThanEqual(Integer duration);
}