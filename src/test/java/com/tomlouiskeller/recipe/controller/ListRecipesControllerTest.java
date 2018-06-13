package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ListRecipesControllerTest {

    private ListRecipesController listRecipesController;
    @Mock
    private RecipeService mockRecipeService;
    @Mock
    private Model mockModel;
    @Mock
    private GeneralConfiguration mockGeneralConfiguration;

//    private final Integer expectedQuickRecipesMaxDuration = 30;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listRecipesController = new ListRecipesController(mockRecipeService, mockGeneralConfiguration);
    }

    // --- getAllRecipes Tests --- ///

    @Test
    public void getAllRecipesMockMvcListPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(listRecipesController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/list/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getAllRecipesReturnString() {
        String actual = listRecipesController.getAllRecipes(mockModel);
        assertNotNull(actual);
    }

    @Test
    public void getAllRecipesReturnSpecificString() {
        String actual = listRecipesController.getAllRecipes(mockModel);
        assertEquals("recipe/list", actual);
    }

    @Test
    public void getAllRecipesCallsRecipeService() {
        listRecipesController.getAllRecipes(mockModel);
        verify(mockRecipeService, times(1)).findAll();
    }

    @Test
    public void getAllRecipesCallsAddAttribute() {
        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);

        listRecipesController.getAllRecipes(mockModel);

        verify(mockModel).addAttribute("recipes", allRecipes);
    }

    @Test // Showing off ArgumentCaptor
    public void getAllRecipesModelReceivesSet() {
        // given
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(Mockito.mock(Recipe.class));
        allRecipes.add(Mockito.mock(Recipe.class));

        when(mockRecipeService.findAll()).thenReturn(allRecipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        listRecipesController.getAllRecipes(mockModel);

        // then
        verify(mockModel, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
        assertEquals(allRecipes, setInController);
    }

    @Test
    public void getAllRecipesModelHasSet() {
        BindingAwareModelMap model = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);

        listRecipesController.getAllRecipes(model);

        assertEquals(allRecipes, model.asMap().get("recipes"));
    }


    // --- getQuickRecipes Tests --- ///

//    @Test
//    public void getQuickRecipesMockMvcQuickRecipesPath() throws Exception{
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(listRecipesController).build();
//        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/list/quick"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
//    }
//
//    @Test
//    public void getQuickRecipesReturnString() {
//        String actual = listRecipesController.getQuickRecipes(mockModel);
//        assertNotNull(actual);
//    }
//
//    @Test
//    public void getQuickRecipesReturnSpecificString() {
//        String actual = listRecipesController.getQuickRecipes(mockModel);
//        assertEquals("recipe/list", actual);
//    }
//
//    @Test
//    public void getQuickRecipesCallsGeneralProperties() {
//        listRecipesController.getQuickRecipes(mockModel);
//        verify(mockGeneralConfiguration, times(1)).getQuickRecipesMaxDuration();
//    }
//
//    @Test
//    public void getQuickRecipesCallsRecipeService() {
//        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
//        listRecipesController.getQuickRecipes(mockModel);
//        verify(mockRecipeService, times(1)).findQuickRecipes(expectedQuickRecipesMaxDuration);
//    }
//
//    @Test
//    public void getQuickRecipesCallsAddAttribute() {
//        Set<Recipe> allRecipes = new HashSet<>();
//        allRecipes.add(Mockito.mock(Recipe.class));
//
//        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
//        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);
//
//        listRecipesController.getQuickRecipes(mockModel);
//        verify(mockModel).addAttribute("recipes", allRecipes);
//    }
//
//    @Test // Showing off ArgumentCaptor
//    public void getQuickRecipesModelReceivesSet() {
//        // given
//        Set<Recipe> allRecipes = new HashSet<>();
//        allRecipes.add(Mockito.mock(Recipe.class));
//        allRecipes.add(Mockito.mock(Recipe.class));
//
//        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
//        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);
//        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
//
//        // when
//        listRecipesController.getQuickRecipes(mockModel);
//
//        // then
//        verify(mockModel, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
//        Set<Recipe> setInController = argumentCaptor.getValue();
//        assertEquals(2, setInController.size());
//        assertEquals(allRecipes, setInController);
//    }
//
//    @Test
//    public void getQuickRecipesHasModelVariableAvailable() {
//        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();
//
//        Recipe recipe = Mockito.mock(Recipe.class);
//        Set<Recipe> allRecipes = new HashSet<>();
//        allRecipes.add(recipe);
//
//        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
//        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);
//
//        listRecipesController.getQuickRecipes(bindingAwareModelMap);
//
//        assertEquals(allRecipes, bindingAwareModelMap.asMap().get("recipes"));
//    }

}