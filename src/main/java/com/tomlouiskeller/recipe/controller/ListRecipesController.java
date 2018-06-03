package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class ListRecipesController {

    private RecipeService recipeService;
    private GeneralConfiguration generalConfiguration;

    public ListRecipesController(RecipeService recipeService, GeneralConfiguration generalConfiguration) {
        this.recipeService = recipeService;
        this.generalConfiguration = generalConfiguration;
    }

    @RequestMapping({"/recipe/list/all"})
    public String getAllRecipes(Model model){
        Set<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/list";
    }

    @RequestMapping({"/recipe/list/quick"})
    public String getQuickRecipes(Model model){
        Set<Recipe> quickRecipes = recipeService.findQuickRecipes(generalConfiguration.getQuickRecipesMaxDuration());
        model.addAttribute("recipes", quickRecipes);
        return "recipe/list";
    }

}
