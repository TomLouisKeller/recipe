package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.form.RecipeForm;
import com.tomlouiskeller.recipe.service.CategoryService;
import com.tomlouiskeller.recipe.service.RecipeFormService;
import com.tomlouiskeller.recipe.service.RecipeService;
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

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewRecipeControllerTest {

    private NewRecipeController newRecipeController;

    @Mock
    private RecipeService recipeService;
    @Mock
    private RecipeFormService recipeFormService;
    @Mock
    private CategoryService categoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newRecipeController = new NewRecipeController(recipeService, recipeFormService, categoryService);
    }

    private MockMvc getMockMvc() {
        return MockMvcBuilders.standaloneSetup(newRecipeController).build();
    }

    // --- GET --- //

    @Test
    public void initCreationFormReturnSpecificString() {
        Model model = mock(Model.class);
        String actual = newRecipeController.initCreationForm(model);
        assertEquals("recipe/form", actual);
    }

    @Test
    public void initCreationFormReturns200() throws Exception {
        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk());
    }

    @Test
    public void initCreationFormRecipeForm() {
        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();
        newRecipeController.initCreationForm(bindingAwareModelMap);
        RecipeForm expected = new RecipeForm();
        expected.setAvailableCategories(new TreeSet<>());
        assertEquals(expected, bindingAwareModelMap.asMap().get("recipeForm"));
    }

    // --- POST --- //

    @Test
    public void processUpdateFormReturnSpecificString() {
        BindingResult result = mock(BindingResult.class);

        Long id = 666L;
        RecipeForm recipeForm = new RecipeForm();
        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeFormService.convert(recipeForm)).thenReturn(recipe);
        when(recipeService.save(recipe)).thenReturn(recipe);

        String actual = newRecipeController.processCreationForm(recipeForm, result);
        assertEquals("redirect:/recipe/" + id + "/show/", actual);
    }


    @Test
    @Ignore // TODO: Add validation and error handling
    public void processUpdateFormHasErrors() throws Exception {
        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(post("/recipe/new")
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
