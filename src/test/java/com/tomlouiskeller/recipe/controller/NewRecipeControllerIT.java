package com.tomlouiskeller.recipe.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewRecipeControllerIT {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private RecipeFormService recipeFormService;
//
//    @Autowired
//    private RecipeService recipeService;

// --- GET Tests --- ///

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipeForm"))
                .andExpect(view().name("recipe/form"));
    }

    // --- POST Tests --- ///

    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/recipe/new")
                .param("recipeTitle", "Pizza")
                .param("recipePreparationDuration", "30")
                .param("recipeCookingDuration", "60")
                .param("recipeServings", "4")
                .param("recipeSource", "yaaa")
                .param("recipeUrl", "yaaa")
                .param("recipeDifficulty", "EASY")
                .param("instructionText", "instructionText")
                .param("nutritionalInfoText", "nutritionalInfoText")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/recipe/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("recipeTitle", "Pizza")
                .param("recipePreparationDuration", "30")
                .param("recipeCookingDuration", "asdf")
                .param("recipeServings", "4")
                .param("recipeUrl", "yaaa")
                .param("recipeDifficulty", "EASY")
                .param("instructionText", "instructionText")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipeForm"))
                .andExpect(model().attributeHasErrors("recipeForm"))
                .andExpect(model().attributeHasFieldErrors("recipeForm", "nutritionalInfoText"))
                .andExpect(model().attributeHasFieldErrors("recipeForm", "recipeCookingDuration"))
                .andExpect(view().name("recipe/form"));
    }
}
