package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;
    private GeneralConfiguration generalConfiguration;

    public IndexController(RecipeService recipeService, GeneralConfiguration generalConfiguration) {
        this.recipeService = recipeService;
        this.generalConfiguration = generalConfiguration;
    }

    @RequestMapping({"", "/", "/index", "/list"})
    public String getAllRecipes(Model model){

        log.debug("Controller: Get All Recipes");
        Set<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/list";
    }

    @RequestMapping({"/quickRecipes"})
    public String getQuickRecipes(Model model){
        log.debug("Controller: Get Quick Recipes");
        Set<Recipe> quickRecipes = recipeService.findQuickRecipes(generalConfiguration.getQuickRecipesMaxDuration());
        model.addAttribute("recipes", quickRecipes);
        return "recipe/list";
    }
}
