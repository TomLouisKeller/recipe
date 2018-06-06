package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExceptionHandlingControllerTest {

    @Test
    public void handleNumberFormatException() throws Exception {
        ShowRecipeController showRecipeController = new ShowRecipeController(mock(RecipeService.class));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(showRecipeController).build();

        mockMvc.perform(
                get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest());
    }
}