package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    private IndexController indexController;
    @Mock
    private RecipeService mockRecipeService;
    @Mock
    private Model mockModel;

    private final Integer expectedQuickRecipesMaxDuration = 30;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(mockRecipeService);
    }

    @Test
    public void getAllRecipesReturnString() {
        String actual = indexController.getAllRecipes(mockModel);
        assertNotNull(actual);
    }

    @Test // This is so pointless.
    public void getAllRecipesReturnSpecificString() {
        String actual = indexController.getAllRecipes(mockModel);
        assertEquals("listRecipes", actual);
    }

    @Test
    public void getAllRecipesCallsService() {
        indexController.getAllRecipes(mockModel);
        verify(mockRecipeService, times(1)).findAll();
    }

    @Test // I don't like this test. This is too much of a lock into the way things are done. Prefer getAllRecipesHasModelVariableAvailable
    public void getAllRecipesCallsAddAttribute() {

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);
        indexController.getAllRecipes(mockModel);
        verify(mockModel).addAttribute("recipes", allRecipes);
    }

    @Test
    public void getAllRecipesHasModelVariableAvailable() {

        BindingAwareModelMap model = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);

        indexController.getAllRecipes(model);

        assertEquals(allRecipes, model.asMap().get("recipes"));
    }

    @Test
    public void getAllRecipesHasModelVariableAvailable2() {

        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);

        indexController.getAllRecipes(bindingAwareModelMap);

        Boolean actual = ((Set) bindingAwareModelMap.asMap().get("recipes")).contains(recipe);
        assertTrue(actual);

    }


    // --- /// --- /// --- /// --- /// --- /// --- /// --- /// --- ///

    @Test
    public void getQuickRecipesReturnString() {
        String actual = indexController.getQuickRecipes(mockModel);
        assertNotNull(actual);
    }

    @Test // This is so pointless.
    public void getQuickRecipesReturnSpecificString() {
        String actual = indexController.getQuickRecipes(mockModel);
        assertEquals("listRecipes", actual);
    }

    // TODO: what happens if we change the 30
    // TODO: Make tests for other duration.
    // TODO: Fetch the 30 from somewhere

    @Test
    public void getQuickRecipesCallsService() {
        indexController.getQuickRecipes(mockModel);
        verify(mockRecipeService, times(1)).findQuickRecipes(expectedQuickRecipesMaxDuration);
    }

    @Test // I don't like this test. This is too much of a lock into the way things are done. Prefer getAllRecipesHasModelVariableAvailable
    public void getQuickRecipesCallsAddAttribute() {

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);
        indexController.getQuickRecipes(mockModel);
        verify(mockModel).addAttribute("recipes", allRecipes);
    }

    @Test
    public void getQuickRecipesHasModelVariableAvailable() {

        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);

        indexController.getQuickRecipes(bindingAwareModelMap);

        assertEquals(allRecipes, bindingAwareModelMap.asMap().get("recipes"));
    }

    @Test
    public void getQuickHasModelVariableAvailable2() {

        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);

        indexController.getQuickRecipes(bindingAwareModelMap);

        Boolean actual = ((Set) bindingAwareModelMap.asMap().get("recipes")).contains(recipe);
        assertTrue(actual);

    }
}