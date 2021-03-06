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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}/edit")
public class UpdateRecipeController {

    private static final String VIEWS_RECIPE_FORM = "recipe/form";

    private RecipeService recipeService;

    private RecipeFormService recipeFormService;
    private CategoryService categoryService;

    public UpdateRecipeController(RecipeService recipeService, RecipeFormService recipeFormService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.recipeFormService = recipeFormService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String initUpdateForm(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(id);
        RecipeForm recipeForm = recipeFormService.convert(recipe, categoryService.findAll());
        model.addAttribute("recipeForm", recipeForm);
        return VIEWS_RECIPE_FORM;
    }

    @PostMapping
    public String processUpdateForm(@PathVariable String id, @Valid RecipeForm recipeForm, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Recipe has input errors while creating. Errors: " + result.getAllErrors());
            return VIEWS_RECIPE_FORM;
        } else {
            Recipe recipe = recipeFormService.convert(recipeForm);
            recipe.setId(id);
            recipe = recipeService.save(recipe);
            return "redirect:/recipe/" + recipe.getId() + "/show/";
        }
    }
}
