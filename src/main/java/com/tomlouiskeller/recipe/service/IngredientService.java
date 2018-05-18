package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Ingredient;

public interface IngredientService {

    Ingredient ingredientFactory(Double amount, String unitOfMeasurementName, String productName);
}
