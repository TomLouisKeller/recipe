package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByTitle(String title);
    Optional<Recipe> findByPreparationDurationIsGreaterThanEqual(Integer duration);
    Optional<Recipe> findByPreparationDurationIsLessThanEqual(Integer duration);
    Optional<Recipe> findByCookingDurationIsGreaterThanEqual(Integer duration);
    Optional<Recipe> findByCookingDurationIsLessThanEqual(Integer duration);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) >= ?1")
    Optional<Recipe> findByPreparationDurationPlusCookingDurationIsGreaterThanEqual(Integer duration);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) <= ?1")
    Optional<Recipe> findByPreparationDurationPlusCookingDurationIsLessThanEqual(Integer duration);

}