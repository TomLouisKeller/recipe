package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// TODO: Get Ingredients sorted by id or introduce sort attribut
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByTitle(String title);

    List<Recipe> findAllByOrderByTitle();

    void deleteById(Long id);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) <= ?1")
    Iterable<Recipe> findByPreparationDurationPlusCookingDurationIsLessThanEqual(Integer duration);

    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) >= ?1")
    Iterable<Recipe> findByPreparationDurationPlusCookingDurationIsGreaterThanEqual(Integer duration);
}