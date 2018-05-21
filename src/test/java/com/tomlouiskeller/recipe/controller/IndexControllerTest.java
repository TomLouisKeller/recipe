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
    private RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getAllRecipesReturnString() {
        String actual = indexController.getAllRecipes(model);
        assertNotNull(actual);
    }

    @Test // This is so pointless.
    public void getAllRecipesReturnSpecificString() {
        String actual = indexController.getAllRecipes(model);
        assertEquals("listRecipes", actual);
    }

    @Test
    public void getAllRecipesCallsService() {
        indexController.getAllRecipes(model);
        verify(recipeService, times(1)).findAll();
    }

    @Test // I don't like this test. This is too much of a lock into the way things are done. Prefer getAllRecipesHasModelVariableAvailable
    public void getAllRecipesCallsAddAttribute() {

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(recipeService.findAll()).thenReturn(allRecipes);
        indexController.getAllRecipes(model);
        verify(model).addAttribute("recipes", allRecipes);
    }

    @Test
    public void getAllRecipesHasModelVariableAvailable() {

        BindingAwareModelMap model = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(recipeService.findAll()).thenReturn(allRecipes);

        indexController.getAllRecipes(model);

        assertEquals(allRecipes, model.asMap().get("recipes"));
    }

    @Test
    public void getAllRecipesHasModelVariableAvailable2() {

        BindingAwareModelMap model = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(recipeService.findAll()).thenReturn(allRecipes);

        indexController.getAllRecipes(model);

        Boolean actual = ((Set) model.asMap().get("recipes")).contains(recipe);
        assertTrue(actual);

    }


    @Test
    public void getQuickRecipes() {
    }
}