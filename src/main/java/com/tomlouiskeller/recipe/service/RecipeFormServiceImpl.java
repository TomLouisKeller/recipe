package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Instruction;
import com.tomlouiskeller.recipe.domain.NutritionalInfo;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

@Service
public class RecipeFormServiceImpl implements RecipeFormService{


    @Override
    public Recipe convert(RecipeForm rF) {
        Recipe recipe = Recipe.builder()
                .id(rF.getRecipeId())
                .title(rF.getRecipeTitle())
                .preparationDuration(rF.getRecipePreparationDuration())
                .cookingDuration(rF.getRecipeCookingDuration())
                .servings(rF.getRecipeServings())
                .source(rF.getRecipeSource())
                .url(rF.getRecipeUrl())
                .image(rF.getRecipeImage()) // TODO: Have to examine this
                .difficulty(rF.getRecipeDifficulty())
                .categories(rF.getRecipeCategories())
                .build();

        Instruction instruction = new Instruction();
        instruction.setId(rF.getInstructionId());
        instruction.setText(rF.getInstructionText());
        instruction.setRecipe(recipe);
        recipe.setInstruction(instruction);

        NutritionalInfo nutritionalInfo= new NutritionalInfo();
        nutritionalInfo.setId(rF.getNutritionalInfoId());
        nutritionalInfo.setText(rF.getNutritionalInfoText());
        nutritionalInfo.setRecipe(recipe);
        recipe.setNutritionalInfo(nutritionalInfo);

        // TODO: convert ingredients

        return recipe;
    }

    @Override
    public RecipeForm convert(Recipe recipe, SortedSet<Category> availableCategories) {

        RecipeForm recipeForm = RecipeForm.builder()
                .recipeId(recipe.getId())
                .recipeTitle(recipe.getTitle())
                .recipePreparationDuration(recipe.getPreparationDuration())
                .recipeCookingDuration(recipe.getCookingDuration())
                .recipeServings(recipe.getServings())
                .recipeSource(recipe.getSource())
                .recipeUrl(recipe.getUrl())
                .recipeImage(recipe.getImage())
                .recipeDifficulty(recipe.getDifficulty())
                .recipeCategories(recipe.getCategories())
                .availableCategories(availableCategories)
                .build();

        Instruction instruction = recipe.getInstruction();
        if(instruction != null){
            recipeForm.setInstructionId(instruction.getId());
            recipeForm.setInstructionText(instruction.getText());
        }

        NutritionalInfo nutritionalInfo = recipe.getNutritionalInfo();
        if(nutritionalInfo != null){
            recipeForm.setNutritionalInfoId(nutritionalInfo.getId());
            recipeForm.setNutritionalInfoText(nutritionalInfo.getText());
        }

        return recipeForm;
    }
}