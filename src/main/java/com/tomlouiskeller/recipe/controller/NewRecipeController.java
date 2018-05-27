package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import com.tomlouiskeller.recipe.service.RecipeFormService;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipe/new")
public class NewRecipeController {

    private static final String VIEWS_RECIPE_FORM = "recipe/form";

    private RecipeService recipeService;
    private RecipeFormService recipeFormService;

    public NewRecipeController(RecipeService recipeService, RecipeFormService recipeFormService) {
        this.recipeService = recipeService;
        this.recipeFormService = recipeFormService;
    }

    @GetMapping
    public String initCreationForm(Model model) {
        model.addAttribute("recipeForm", new RecipeForm());
        return VIEWS_RECIPE_FORM;
    }

    @PostMapping
    public String processCreationForm(@Valid RecipeForm recipeForm, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_RECIPE_FORM;
        } else {
            Recipe recipe = recipeFormService.convert(recipeForm);
            recipe = recipeService.save(recipe);
            return "redirect:/recipe/" + recipe.getId() + "/show/";
        }
    }

}
