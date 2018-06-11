package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.*;
import com.tomlouiskeller.recipe.form.RecipeForm;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


// TODO: convert image both ways
public class RecipeFormServiceImplTest {

    private RecipeFormServiceImpl recipeFormService;


    @Before
    public void setUp() {
        recipeFormService = new RecipeFormServiceImpl();
    }

    // --- Convert RecipeForm to Recipe --- //

    @Test
    public void convertFormToEntityIsNotNull() {
        RecipeForm recipeForm = mock(RecipeForm.class);
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertNotNull(recipe);
    }


    // TODO: Check id
    // TODO: Move instructionText and nutritionalInfoText out
    // TODO: Test instructionId and nutritionalId
    // TODO: Rename convertFormToEntityStandardFields to convertFormToEntityRecipeFields
    @Test
    public void convertFormToEntityStandardFields() {
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
    public void convertFormToEntityCategories() {

        Category american = new Category("American");
        Category swiss = new Category("Swiss");
        Set<Category> recipeCategories = new HashSet<>(Arrays.asList(american, swiss));
        RecipeForm recipeForm = RecipeForm.builder()
                .recipeCategories(recipeCategories)
                .build();
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertEquals(recipeCategories, recipe.getCategories());

    }

    // --- Convert Recipe to RecipeForm --- //

    @Test
    public void convertEntityToFormIsNotNull() {
        Recipe recipe = mock(Recipe.class);
        RecipeForm recipeForm = recipeFormService.convert(recipe, null);
        assertNotNull(recipeForm);
    }

    @Test
    public void convertEntityToFormRecipeFields() {
        String recipeTitle = "Pizza";
        Integer recipePreparationDuration = 30;
        Integer recipeCookingDuration = 60;
        Integer recipeServings = 4;
        String recipeSource = "yaaa";
        String recipeUrl = "yaaa.com/pizza";
        Difficulty recipeDifficulty = Difficulty.EASY;

        Instruction instruction = mock(Instruction.class);
        NutritionalInfo nutritionalInfo = mock(NutritionalInfo.class);

        Recipe recipe = Recipe.builder()
                .title(recipeTitle)
                .preparationDuration(recipePreparationDuration)
                .cookingDuration(recipeCookingDuration)
                .servings(recipeServings)
                .source(recipeSource)
                .url(recipeUrl)
                .difficulty(recipeDifficulty)
                .instruction(instruction)
                .nutritionalInfo(nutritionalInfo)
                .build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);

        assertEquals(recipeTitle, recipeForm.getRecipeTitle());
        assertEquals(recipePreparationDuration, recipeForm.getRecipePreparationDuration());
        assertEquals(recipeCookingDuration, recipeForm.getRecipeCookingDuration());
        assertEquals(recipeServings, recipeForm.getRecipeServings());
        assertEquals(recipeSource, recipeForm.getRecipeSource());
        assertEquals(recipeUrl, recipeForm.getRecipeUrl());
        assertEquals(recipeDifficulty, recipeForm.getRecipeDifficulty());
    }

    @Test
    public void convertEntityToFormInstruction() {
        Instruction instruction = new Instruction();
        instruction.setId(333L);
        instruction.setText("Blub");

        Recipe recipe = Recipe.builder()
                .instruction(instruction)
                .build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);

        assertEquals(instruction.getId(), recipeForm.getInstructionId());
        assertEquals(instruction.getText(), recipeForm.getInstructionText());
    }

    @Test
    public void convertEntityToFormNutritionalInfo() {
        NutritionalInfo nutritionalInfo = new NutritionalInfo();
        nutritionalInfo.setId(222L);
        nutritionalInfo.setText("nutritionalInfo");

        Recipe recipe = Recipe.builder()
                .nutritionalInfo(nutritionalInfo)
                .build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);
        assertEquals(nutritionalInfo.getId(), recipeForm.getNutritionalInfoId());
        assertEquals(nutritionalInfo.getText(), recipeForm.getNutritionalInfoText());
    }

    @Test
    public void convertEntityToFormRecipeCategories() {

        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        List<Category> categoryList = Arrays.asList(category1, category2);
        Set<Category> categorySet = new HashSet<>(categoryList);

        Recipe recipe = new Recipe();
        recipe.setCategories(categorySet);

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);
        assertEquals(categorySet, recipeForm.getRecipeCategories());
    }

    @Test
    public void convertEntityToFormAvailableCategories() {
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        List<Category> categoryList = Arrays.asList(category1, category2);
        SortedSet<Category> categorySet = new TreeSet<>(categoryList);

        Recipe recipe = Recipe.builder().build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, categorySet);
        assertEquals(categorySet, recipeForm.getAvailableCategories());
    }
}