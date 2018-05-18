package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}