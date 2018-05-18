package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private RecipeRepository recipeRepository;

    public IndexController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getAllRecipes(Model model){
        Iterable<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "listRecipes";
    }

    @RequestMapping({"/quickRecipes"})
    public String getQuickRecipes(Model model){
        Iterable<Recipe> quickRecipes = recipeRepository.findByPreparationDurationPlusCookingDurationIsLessThanEqual(30);
        model.addAttribute("recipes", quickRecipes);
        return "listRecipes";
    }
}
