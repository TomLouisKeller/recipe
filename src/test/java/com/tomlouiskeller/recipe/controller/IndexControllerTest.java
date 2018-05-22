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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class IndexControllerTest {

    private IndexController indexController;
    @Mock
    private RecipeService mockRecipeService;
    @Mock
    private Model mockModel;
    @Mock
    private GeneralConfiguration mockGeneralConfiguration;

    private final Integer expectedQuickRecipesMaxDuration = 30;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(mockRecipeService, mockGeneralConfiguration);
    }

    // --- getAllRecipes Tests --- ///

    @Test
    public void getAllRecipesMockMvcRootPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getAllRecipesMockMvcDashPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getAllRecipesMockMvcIndexPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getAllRecipesMockMvcListPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getAllRecipesReturnString() {
        String actual = indexController.getAllRecipes(mockModel);
        assertNotNull(actual);
    }

    @Test
    public void getAllRecipesReturnSpecificString() {
        String actual = indexController.getAllRecipes(mockModel);
        assertEquals("recipe/list", actual);
    }

    @Test
    public void getAllRecipesCallsRecipeService() {
        indexController.getAllRecipes(mockModel);
        verify(mockRecipeService, times(1)).findAll();
    }

    @Test
    public void getAllRecipesCallsAddAttribute() {
        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockRecipeService.findAll()).thenReturn(allRecipes);

        indexController.getAllRecipes(mockModel);

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
        indexController.getAllRecipes(mockModel);

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

        indexController.getAllRecipes(model);

        assertEquals(allRecipes, model.asMap().get("recipes"));
    }


    // --- getQuickRecipes Tests --- ///

    @Test
    public void getQuickRecipesMockMvcQuickRecipesPath() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/quickRecipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    public void getQuickRecipesReturnString() {
        String actual = indexController.getQuickRecipes(mockModel);
        assertNotNull(actual);
    }

    @Test
    public void getQuickRecipesReturnSpecificString() {
        String actual = indexController.getQuickRecipes(mockModel);
        assertEquals("recipe/list", actual);
    }

    @Test
    public void getQuickRecipesCallsGeneralProperties() {
        indexController.getQuickRecipes(mockModel);
        verify(mockGeneralConfiguration, times(1)).getQuickRecipesMaxDuration();
    }

    @Test
    public void getQuickRecipesCallsRecipeService() {
        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
        indexController.getQuickRecipes(mockModel);
        verify(mockRecipeService, times(1)).findQuickRecipes(expectedQuickRecipesMaxDuration);
    }

    @Test
    public void getQuickRecipesCallsAddAttribute() {
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(Mockito.mock(Recipe.class));

        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);

        indexController.getQuickRecipes(mockModel);
        verify(mockModel).addAttribute("recipes", allRecipes);
    }

    @Test // Showing off ArgumentCaptor
    public void getQuickRecipesModelReceivesSet() {
        // given
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(Mockito.mock(Recipe.class));
        allRecipes.add(Mockito.mock(Recipe.class));

        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        indexController.getQuickRecipes(mockModel);

        // then
        verify(mockModel, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
        assertEquals(allRecipes, setInController);
    }

    @Test
    public void getQuickRecipesHasModelVariableAvailable() {
        BindingAwareModelMap bindingAwareModelMap = new BindingAwareModelMap();

        Recipe recipe = Mockito.mock(Recipe.class);
        Set<Recipe> allRecipes = new HashSet<>();
        allRecipes.add(recipe);

        when(mockGeneralConfiguration.getQuickRecipesMaxDuration()).thenReturn(expectedQuickRecipesMaxDuration);
        when(mockRecipeService.findQuickRecipes(expectedQuickRecipesMaxDuration)).thenReturn(allRecipes);

        indexController.getQuickRecipes(bindingAwareModelMap);

        assertEquals(allRecipes, bindingAwareModelMap.asMap().get("recipes"));
    }

    // --- showRecipe Tests --- ///

    @Test
    public void showRecipeReturnString() {
        String actual = indexController.showRecipe(1L, mockModel);
        assertNotNull(actual);
    }

    @Test
    public void showRecipeReturnSpecificString() {
        String actual = indexController.showRecipe(1L, mockModel);
        assertEquals("recipe/show", actual);
    }

    @Test
    public void getQuickRecipesGetsStatusCode200() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void showRecipeUsesRecipeService() {
        indexController.showRecipe(1L, mockModel);
        verify(mockRecipeService, times(1)).findById(anyLong());
    }

    @Test
    public void showRecipeUsesRecipeServiceWithSpecificLong() {
        Long id = 13456L;
        indexController.showRecipe(id, mockModel);
        verify(mockRecipeService, times(1)).findById(id);
    }

    @Test
    public void showRecipeSetsRecipeIntoModel() {
        Long id = 13456L;
        Recipe recipe = Mockito.mock(Recipe.class);

        when(mockRecipeService.findById(id)).thenReturn(recipe);

        indexController.showRecipe(id, mockModel);

        verify(mockModel, times(1)).addAttribute("recipe", recipe);
    }

    @Test
    public void getQuickRecipesRecipeIsInModel() throws Exception{
        Recipe recipe = Mockito.mock(Recipe.class);
        when(mockRecipeService.findById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(model().attributeExists("recipe"));
    }


    // TODO: Test for the exception and handle it!!!
}