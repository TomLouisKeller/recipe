package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteRecipeControllerTest {

    private DeleteRecipeController deleteRecipeController;
    @Mock
    private RecipeService mockRecipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deleteRecipeController = new DeleteRecipeController(mockRecipeService);
    }

    // --- GET --- //

    @Test
    public void deleteRecipeReturnsSpecificString() {
        String id = "234";
        String actual = deleteRecipeController.deleteRecipe(id);
        assertEquals("redirect:/recipe/list/all", actual);
    }

    @Test
    public void deleteRecipeReturns3xx() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(deleteRecipeController).build();
        mockMvc.perform(get("/recipe/321/delete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteRecipeCallsService() {
        String id = "567";
        deleteRecipeController.deleteRecipe(id);
        verify(mockRecipeService, times(1)).deleteById(id);
    }
}

