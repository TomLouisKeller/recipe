package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import com.tomlouiskeller.recipe.service.CategoryService;
import com.tomlouiskeller.recipe.service.RecipeFormService;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe/new")
public class NewRecipeController {

    private static final String VIEWS_RECIPE_FORM = "recipe/form";

    private RecipeService recipeService;
    private RecipeFormService recipeFormService;
    private CategoryService categoryService;

    public NewRecipeController(RecipeService recipeService, RecipeFormService recipeFormService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.recipeFormService = recipeFormService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String initCreationForm(Model model) {
        RecipeForm recipeForm = new RecipeForm();
        recipeForm.setAvailableCategories(categoryService.findAll());
        model.addAttribute("recipeForm", recipeForm);
        return VIEWS_RECIPE_FORM;
    }

    @PostMapping
    public String processCreationForm(@Valid RecipeForm recipeForm, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Recipe has input errors while creating. Errors: " + result.getAllErrors());
            return VIEWS_RECIPE_FORM;
        } else {
            Recipe recipe = recipeFormService.convert(recipeForm);
            recipe = recipeService.save(recipe);
            return "redirect:/recipe/" + recipe.getId() + "/show/";
        }
    }

}
