package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import com.tomlouiskeller.recipe.service.RecipeFormService;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class UpdateRecipeControllerTest {

    private UpdateRecipeController updateRecipeController;

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeFormService recipeFormService;

    private Long id;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        updateRecipeController = new UpdateRecipeController(recipeService, recipeFormService);
    }

    // --- GET --- //

    @Test
    public void initUpdateFormSpecificString() {
        String actual = updateRecipeController.initUpdateForm(id, model);
        assertEquals("recipe/form", actual);
    }

    @Test
    public void initUpdateFormReturns200() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(updateRecipeController).build();
        mockMvc.perform(get("/recipe/1/edit"))
                .andExpect(status().isOk());
    }

    @Test
    public void initUpdateFormCallsService() {
        id = 456L;
        updateRecipeController.initUpdateForm(id, model); // recipeService
        verify(recipeService, times(1)).findById(id);
    }

    @Test
    public void initUpdateFormIsRecipeFormSet() {
        id = 555L;
        Recipe recipe = Recipe.builder().id(id).build();
        RecipeForm recipeForm = RecipeForm.builder().recipeId(id).build();
        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();

        when(recipeService.findById(id)).thenReturn(recipe);
        when(recipeFormService.convert(recipe, null)).thenReturn(recipeForm);

        updateRecipeController.initUpdateForm(id, bindingAwareModelMap);

        RecipeForm actual = (RecipeForm) bindingAwareModelMap.asMap().get("recipeForm");
        assertEquals(recipeForm, actual);
        assertEquals(id, actual.getRecipeId());
    }


    // --- POST --- //

    @Test // This should fail with errors, no?
    public void processUpdateFormReturnSpecificString() {
        BindingResult result = mock(BindingResult.class);

        Long id = 623L;
        RecipeForm recipeForm = new RecipeForm();
        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeFormService.convert(recipeForm)).thenReturn(recipe);
        when(recipeService.save(recipe)).thenReturn(recipe);

        String actual = updateRecipeController.processUpdateForm(id, recipeForm, result);
        assertEquals("redirect:/recipe/" + id + "/show/", actual);
    }

    @Test
    @Ignore // TODO: Add validation and error handling
    public void processUpdateFormHasErrors() throws Exception {
        Long id = 445L;
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(updateRecipeController).build();
        mockMvc.perform(post("/owners/{id}/edit", id)
                .param("recipeTitle", "Pizza")
                .param("recipePreparationDuration", "30")
                .param("recipeCookingDuration", "60")
                .param("recipeServings", "4")
                .param("recipeUrl", "yaaa")
                .param("recipeDifficulty", "EASY")
                .param("instructionText", "instructionText")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("recipeForm"))
                .andExpect(model().attributeHasFieldErrors("recipeForm", "nutritionalInfoText"))
                .andExpect(model().attributeHasFieldErrors("recipeForm", "recipeSource"))
                .andExpect(view().name("recipe/form"));
    }

}
