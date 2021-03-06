package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Ingredient;
import com.tomlouiskeller.recipe.domain.Product;
import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final UnitOfMeasurementService unitOfMeasurementService;
    private final ProductService productService;

    public IngredientServiceImpl(UnitOfMeasurementService unitOfMeasurementService, ProductService productService) {
        this.unitOfMeasurementService = unitOfMeasurementService;
        this.productService = productService;
    }

    public Ingredient ingredientFactory(Double amount, String unitOfMeasurementName, String productName){
        UnitOfMeasurement unitOfMeasurement = unitOfMeasurementService.getByName(unitOfMeasurementName);
        Product product = productService.getByName(productName);
        Ingredient ingredient = new Ingredient(amount, unitOfMeasurement, product); // We always want to create a new Ingredient in the database, even if it's duplicate, so we can just delete and update it
        return ingredient;
    }
}
