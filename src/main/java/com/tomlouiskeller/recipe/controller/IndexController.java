package com.tomlouiskeller.recipe.controller;


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

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getAllRecipes(Model model){
        log.debug("Controller: Get All Recipes");
        Set<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "listRecipes";
    }

    @RequestMapping({"/quickRecipes"})
    public String getQuickRecipes(Model model){
        log.debug("Controller: Get Quick Recipes");
        Set<Recipe> quickRecipes = recipeService.findQuickRecipes(30);
        model.addAttribute("recipes", quickRecipes);
        return "listRecipes";
    }
}
