package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ErrorHandlingControllerTest {

    private ShowRecipeController showRecipeController;

    private ErrorHandlingController errorHandlingController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private GeneralConfiguration generalConfiguration;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        showRecipeController = new ShowRecipeController(recipeService);
        errorHandlingController = new ErrorHandlingController(generalConfiguration);

        mockMvc = MockMvcBuilders.standaloneSetup(showRecipeController)
                .setControllerAdvice(errorHandlingController)
                .build();
    }

    @Test
    public void handleRecipeNotFoundException() throws Exception {
        String id = "789";

        when(recipeService.findById(id)).thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(get("/recipe/" + id + "/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/generic"));
    }

    @Test
    @Ignore // TODO: Figure out how to test this properly
    public void handleResourceNotFound() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(errorHandlingController)
                .setControllerAdvice(errorHandlingController)
                .build();

        mockMvc.perform(
                get("/recipe/blub"))
                .andExpect(status().isNotFound())
                .andExpect(model().attributeExists("title"))
                .andExpect(view().name("error/generic"));
    }
}