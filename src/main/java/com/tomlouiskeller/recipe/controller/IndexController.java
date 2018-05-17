package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.repository.CategoryRepository;
import com.tomlouiskeller.recipe.repository.ProductRepository;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public IndexController(RecipeRepository recipeRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        Optional<Recipe> recipe = recipeRepository.findByTitle("Rösti");
        Optional<Category> category = categoryRepository.findByName("German");

        System.out.println("Recipe is " + recipe.get().getTitle() + " Id: " + recipe.get().getId());
        System.out.println("Category is " + category.get().getName() + " Id: " + category.get().getId());

        Optional<Recipe> recipe2 = recipeRepository.findByPreparationDurationPlusCookingDurationIsLessThanEqual(60);
        System.out.println("Recipe that is made in less then 60 min: " + recipe2.get().getTitle() + " Id: " + recipe.get().getId());

        return "index";
    }

}
