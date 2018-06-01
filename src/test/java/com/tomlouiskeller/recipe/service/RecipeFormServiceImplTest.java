package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.domain.Difficulty;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class RecipeFormServiceImplTest {

    private RecipeFormServiceImpl recipeFormService;


    @Before
    public void setUp() throws Exception {
        recipeFormService = new RecipeFormServiceImpl();
    }

    @Test
    public void convertFormToEntityIsNotNull() {
        RecipeForm recipeForm = mock(RecipeForm.class);
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertNotNull(recipe);
    }

    @Test
    public void convertStandardFields() {
        String recipeTitle = "Pizza";
        Integer recipePreparationDuration = 30;
        Integer recipeCookingDuration = 60;
        Integer recipeServings = 4;
        String recipeSource = "yaaa";
        String recipeUrl = "yaaa.com/pizza";
        Difficulty recipeDifficulty = Difficulty.EASY;
        String instructionText = "instructionText";
        String nutritionalInfoText = "nutritionalInfoText";
        RecipeForm recipeForm = RecipeForm.builder()
                                .recipeTitle(recipeTitle)
                                .recipePreparationDuration(recipePreparationDuration)
                                .recipeCookingDuration(recipeCookingDuration)
                                .recipeServings(recipeServings)
                                .recipeSource(recipeSource)
                                .recipeUrl(recipeUrl)
                                .recipeDifficulty(recipeDifficulty)
                                .instructionText(instructionText)
                                .nutritionalInfoText(nutritionalInfoText)
                                .build();

        Recipe recipe = recipeFormService.convert(recipeForm);

        assertEquals(recipeTitle, recipe.getTitle());
        assertEquals(recipePreparationDuration, recipe.getPreparationDuration());
        assertEquals(recipeCookingDuration, recipe.getCookingDuration());
        assertEquals(recipeServings, recipe.getServings());
        assertEquals(recipeSource, recipe.getSource());
        assertEquals(recipeUrl, recipe.getUrl());
        assertEquals(recipeDifficulty, recipe.getDifficulty());
        assertEquals(instructionText, recipe.getInstruction().getText());
        assertEquals(nutritionalInfoText, recipe.getNutritionalInfo().getText());
    }

    @Test
    public void convertCategories() {

        Category american = new Category("American");
        Category swiss = new Category("Swiss");
        Set<Category> recipeCategories = new HashSet<>(Arrays.asList(american, swiss));
        RecipeForm recipeForm = RecipeForm.builder()
                .recipeCategories(recipeCategories)
                .build();
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertEquals(recipeCategories, recipe.getCategories());

    }

    // TODO: Check if categories are set.
}