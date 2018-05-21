package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    private RecipeService recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void findAllWithEmptyDatabase() {
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(0, actual.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findAllWithOneEntry() {

        Recipe recipe = Mockito.mock(Recipe.class);
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(allRecipes);

        Set<Recipe> actual = recipeService.findAll();
        assertEquals(1, actual.size());
        assertEquals(true, actual.contains(recipe));
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findAllWithMultipleEntries() {

        int expected = 10;
        List<Recipe> allRecipes = new ArrayList<>();
        for (int i = 0; i < expected; i++) {
            Recipe recipe = Mockito.mock(Recipe.class, "" + i);
            allRecipes.add(recipe);
        }

        when(recipeRepository.findAll()).thenReturn(allRecipes);
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(expected, actual.size());
        assertEquals(true, actual.contains(allRecipes.get(3)));
        verify(recipeRepository, times(1)).findAll();
    }



    @Test
    public void findQuickRecipes() {
    }

    @Test
    public void save() {
    }

    @Test
    public void saveAll() {
    }
}