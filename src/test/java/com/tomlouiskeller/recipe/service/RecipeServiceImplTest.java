package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository mockRecipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(mockRecipeRepository);
    }

    @Test
    public void findAllWithEmptyDatabase() {
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(0, actual.size());
        verify(mockRecipeRepository, times(1)).findAllByOrderByTitle();
    }

    @Test
    public void findAllWithOneEntry() {

        Recipe recipe = Mockito.mock(Recipe.class);
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(recipe);

        when(mockRecipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);

        Set<Recipe> actual = recipeService.findAll();
        assertEquals(1, actual.size());
        assertEquals(true, actual.contains(recipe));
        verify(mockRecipeRepository, times(1)).findAllByOrderByTitle();
    }

    @Test
    public void findAllWithMultipleEntries() {

        int expected = 10;
        List<Recipe> allRecipes = new ArrayList<>();
        for (int i = 0; i < expected; i++) {
            Recipe recipe = Mockito.mock(Recipe.class, "" + i);
            allRecipes.add(recipe);
        }

        when(mockRecipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(expected, actual.size());
        assertEquals(true, actual.contains(allRecipes.get(3)));
        verify(mockRecipeRepository, times(1)).findAllByOrderByTitle();
    }

//    @Test
//    public void findAllIsSortedByTitle() {
//
//        int expected = 10;
//        List<Recipe> allRecipes = new ArrayList<>();
//        for (int i = 0; i < expected; i++) {
//            Recipe recipe = Mockito.mock(Recipe.class, "" + i);
//            allRecipes.add(recipe);
//        }
//
//        when(mockRecipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);
//        Set<Recipe> actual = recipeService.findAll();
//        assertEquals(expected, actual.size());
//        assertEquals(true, actual.contains(allRecipes.get(3)));
//        verify(mockRecipeRepository, times(1)).findAllByOrderByTitle();
//    }



// TODO:
//    @Test
//    public void findQuickRecipes() {
//    }
//
//    @Test
//    public void save() {
//    }
//
//    @Test
//    public void saveAll() {
//    }

    @Test
    public void getByIdCallsRepository(){
        // given
        Long id = 1L;

        // when
        try {
            recipeService.findById(id);
        } catch (Exception e){
            // In this test we only want to test if it calls the repository.
            // Therefore we ignore the exception that is thrown.
        }

        // then
        verify(mockRecipeRepository, times(1)).findById(id);
    }

    @Test
    public void getByIdGetsActualRecipe(){
        // given
        Long id = 1L;
        Recipe recipe = Mockito.mock(Recipe.class);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(mockRecipeRepository.findById(id)).thenReturn(optionalRecipe);

        // when
        Recipe actual = recipeService.findById(id);

        // then
        assertEquals(recipe, actual);
    }

    @Test(expected = RecipeNotFoundException.class)
    public void getByIdNotAvailable(){
        recipeService.findById(1L);
    }

    // -- Delete -- //


    @Test
    public void deleteById() {
        Long id = 432L;
        recipeService.deleteById(id);
        verify(mockRecipeRepository, times(1)).deleteById(id);
    }
}