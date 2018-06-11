package com.tomlouiskeller.recipe.bootstrap;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import com.tomlouiskeller.recipe.service.CategoryService;
import com.tomlouiskeller.recipe.service.UnitOfMeasurementService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicBootstrap {

    private final CategoryService categoryService;
    private final UnitOfMeasurementService unitOfMeasurementService;

    public BasicBootstrap(CategoryService categoryService, UnitOfMeasurementService unitOfMeasurementService) {
        this.categoryService = categoryService;
        this.unitOfMeasurementService = unitOfMeasurementService;
    }

    public void initCategories(){
        if (categoryService.count() == 0L){
            List<Category> bootStrapCategories = createProductionCategories();
            categoryService.saveAll(bootStrapCategories);
        }
    }

    public void initUnitOfMeasurements(){
        if (unitOfMeasurementService.count() == 0L){
            List<UnitOfMeasurement> bootStrapUnitOfMeasurements = createProductionUnitOfMeasurements();
            unitOfMeasurementService.saveAll(bootStrapUnitOfMeasurements);
        }
    }


    public List<Category> createProductionCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Vegetarian"));
        categories.add(new Category("Vegan"));
        categories.add(new Category("Fish"));
        categories.add(new Category("Beef"));
        categories.add(new Category("Chicken"));
        categories.add(new Category("Pork"));
        categories.add(new Category("Italian"));
        categories.add(new Category("Mexican"));
        categories.add(new Category("Chinese"));
        categories.add(new Category("Thai"));
        categories.add(new Category("German"));
        categories.add(new Category("Swiss"));
        categories.add(new Category("American"));
        return categories;
    }

    public List<UnitOfMeasurement> createProductionUnitOfMeasurements(){
        List<UnitOfMeasurement> unitOfMeasurements = new ArrayList<>();
        unitOfMeasurements.add(new UnitOfMeasurement(""));
        unitOfMeasurements.add(new UnitOfMeasurement("Tablespoon"));
        unitOfMeasurements.add(new UnitOfMeasurement("Teaspoon"));
        unitOfMeasurements.add(new UnitOfMeasurement("Cup"));
        unitOfMeasurements.add(new UnitOfMeasurement("Liter"));
        unitOfMeasurements.add(new UnitOfMeasurement("Milliliter"));
        unitOfMeasurements.add(new UnitOfMeasurement("Kilogram"));
        unitOfMeasurements.add(new UnitOfMeasurement("Gram"));
        unitOfMeasurements.add(new UnitOfMeasurement("Slice"));
        unitOfMeasurements.add(new UnitOfMeasurement("Some"));
        unitOfMeasurements.add(new UnitOfMeasurement("Pinch"));
        unitOfMeasurements.add(new UnitOfMeasurement("Can"));
        unitOfMeasurements.add(new UnitOfMeasurement("Dash"));
        return unitOfMeasurements;
    }

}
