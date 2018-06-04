package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe/{id}/delete")
public class DeleteRecipeController {

    private RecipeService recipeService;

    public DeleteRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/recipe/list/all";
    }
}