package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.form.RecipeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe/form")
public class RecipeController{

        @GetMapping
        public String getRecipeForm(Model model) {
            model.addAttribute("recipeForm", new RecipeForm());
            return "recipe/form";
        }
}
