package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShowRecipeControllerTest {

    private ShowRecipeController showRecipeController;
    @Mock
    private RecipeService mockRecipeService;
    @Mock
    private Model mockModel;
    private String id;

    @Before
    public void setUp() {

        id = Math.random() + "";
        MockitoAnnotations.initMocks(this);
        showRecipeController = new ShowRecipeController(mockRecipeService);

    }

    // --- showRecipe Tests --- ///

    @Test
    public void showRecipeReturnString() {
        String actual = showRecipeController.showRecipe(id, mockModel);
        assertNotNull(actual);
    }

    @Test
    public void showRecipeReturnSpecificString() {
        String actual = showRecipeController.showRecipe(id, mockModel);
        assertEquals("recipe/show", actual);
    }

    @Test
    public void getQuickRecipesGetsStatusCode200() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(showRecipeController).build();
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk());
    }

    @Test
    public void showRecipeUsesRecipeService() {
        showRecipeController.showRecipe(id, mockModel);
        verify(mockRecipeService, times(1)).findById(id);
    }

    @Test
    public void showRecipeUsesRecipeServiceWithSpecificLong() {
        showRecipeController.showRecipe(id, mockModel);
        verify(mockRecipeService, times(1)).findById(id);
    }

    @Test
    public void getQuickRecipesRecipeIsInModel() throws Exception {
        Recipe recipe = Mockito.mock(Recipe.class);
        when(mockRecipeService.findById(id)).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(showRecipeController).build();
        mockMvc.perform(get("/recipe/" + id + "/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void showRecipeSetsRecipeIntoModel() {
        Recipe recipe = Mockito.mock(Recipe.class);

        when(mockRecipeService.findById(id)).thenReturn(recipe);

        showRecipeController.showRecipe(id, mockModel);

        verify(mockModel, times(1)).addAttribute("recipe", recipe);
    }
}