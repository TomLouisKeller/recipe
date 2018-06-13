package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// TODO: Get Ingredients sorted by id or introduce sort attribute
public interface RecipeRepository extends CrudRepository<Recipe, String> {

    Optional<Recipe> findByTitle(String title);

    List<Recipe> findAllByOrderByTitle();

    void deleteById(String id);

//    @Query("select r from Recipe r where (r.preparationDuration + r.cookingDuration) <= ?1")
//    Iterable<Recipe> findRecipesQuickerThan(Integer duration);
}