package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ExceptionHandlingControllerTest {

    private ShowRecipeController showRecipeController;

    private ExceptionHandlingController controllerExceptionHandler;

    @Mock
    private RecipeService recipeService;

    @Mock
    private GeneralConfiguration generalConfiguration;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        showRecipeController = new ShowRecipeController(recipeService);
        controllerExceptionHandler = new ExceptionHandlingController(generalConfiguration);

        mockMvc = MockMvcBuilders.standaloneSetup(showRecipeController)
                .setControllerAdvice(controllerExceptionHandler)
                .build();
    }

    @Test
    public void handleRecipeNotFoundException() throws Exception {
        Long id = 789L;

        when(recipeService.findById(id)).thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(get("/recipe/" + id + "/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/generic_exception"));
    }

    @Test
    public void handleNumberFormatException() throws Exception {
        String id = "asdf";

        mockMvc.perform(
                get("/recipe/" + id + "/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/generic_exception"));
    }
}