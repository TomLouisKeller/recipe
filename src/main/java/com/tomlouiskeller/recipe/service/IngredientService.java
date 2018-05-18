package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Ingredient;
import com.tomlouiskeller.recipe.domain.Product;
import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import com.tomlouiskeller.recipe.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasurementService unitOfMeasurementService;
    private final ProductService productService;


    public IngredientService(IngredientRepository ingredientRepository, UnitOfMeasurementService unitOfMeasurementService, ProductService productService) {
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasurementService = unitOfMeasurementService;
        this.productService = productService;
    }

    public Ingredient ingredientFactory(Double amount, String unitOfMeasurementName, String productName){
        UnitOfMeasurement unitOfMeasurement = unitOfMeasurementService.findByNameOrCreate(unitOfMeasurementName);
        Product product = productService.findByNameOrCreate(productName);
        // We always want to create a new Ingredient in the database, even if it's duplicate, so we can just delete and update it
        Ingredient ingredient = new Ingredient(amount, unitOfMeasurement, product);
        ingredientRepository.save(ingredient);
        return ingredient;
    }
}
