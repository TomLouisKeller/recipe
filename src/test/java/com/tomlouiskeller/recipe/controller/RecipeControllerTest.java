package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.form.RecipeForm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecipeControllerTest {

    private RecipeController recipeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController();
    }

    @Test
    public void getRecipeFormReturnsString() {

        Model model = Mockito.mock(Model.class);
        String actual = recipeController.getRecipeForm(model);
        assertNotNull(actual);
    }

    @Test
    public void getRecipeFormReturnSpecificString() {
        Model model = Mockito.mock(Model.class);
        String actual = recipeController.getRecipeForm(model);
        assertEquals("recipe/form", actual);
    }

    @Test
    public void getRecipeFormReturns200() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/form"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getRecipeFormIsRecipeFormSet() {
        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();
        recipeController.getRecipeForm(bindingAwareModelMap);
        assertEquals(new RecipeForm(), bindingAwareModelMap.asMap().get("recipeForm"));
    }

}
