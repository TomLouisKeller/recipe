package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    private String id;

    @Before
    public void setUp() {
        id = Math.random() + "";
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void findAllWithEmptyDatabase() {
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(0, actual.size());
        verify(recipeRepository, times(1)).findAllByOrderByTitle();
    }

    @Test
    public void findAllWithOneEntry() {

        Recipe recipe = Mockito.mock(Recipe.class);
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(recipe);

        when(recipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);

        Set<Recipe> actual = recipeService.findAll();
        assertEquals(1, actual.size());
        assertEquals(true, actual.contains(recipe));
        verify(recipeRepository, times(1)).findAllByOrderByTitle();
    }

    @Test
    public void findAllWithMultipleEntries() {

        int expected = 10;
        List<Recipe> allRecipes = new ArrayList<>();
        for (int i = 0; i < expected; i++) {
            Recipe recipe = Mockito.mock(Recipe.class, "" + i);
            allRecipes.add(recipe);
        }

        when(recipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);
        Set<Recipe> actual = recipeService.findAll();
        assertEquals(expected, actual.size());
        assertEquals(true, actual.contains(allRecipes.get(3)));
        verify(recipeRepository, times(1)).findAllByOrderByTitle();
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
//        when(recipeRepository.findAllByOrderByTitle()).thenReturn(allRecipes);
//        Set<Recipe> actual = recipeService.findAll();
//        assertEquals(expected, actual.size());
//        assertEquals(true, actual.contains(allRecipes.get(3)));
//        verify(recipeRepository, times(1)).findAllByOrderByTitle();
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
        Recipe recipe = mock(Recipe.class);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        // when
        recipeService.findById(id);

        // then
        verify(recipeRepository, times(1)).findById(id);
    }

    @Test
    public void getByIdGetsActualRecipe(){
        // given
        Recipe recipe = Mockito.mock(Recipe.class);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        // when
        Recipe actual = recipeService.findById(id);

        // then
        assertEquals(recipe, actual);
    }

    @Test(expected = RecipeNotFoundException.class)
    public void getByIdNotAvailable(){
        recipeService.findById(id);
    }

    // -- Delete -- //


    @Test
    public void deleteById() {
        recipeService.deleteById(id);
        verify(recipeRepository, times(1)).deleteById(id);
    }

    // -- saveImage -- //

    @Test
    public void saveImageCallsRepository() {
        byte[] bytes = "saveImageCallsRepository".getBytes();
        Recipe recipe = mock(Recipe.class);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        Byte[] bytesAsObject = ArrayUtils.toObject(bytes);
        recipeService.saveImage(id, bytesAsObject);

        verify(recipeRepository).save(recipe);
    }

    @Test
    public void saveImageStoresImageInRecipe() {
        byte[] bytes = "saveImageStoresImageInRecipe".getBytes();
        Recipe recipe = new Recipe();
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);

        Byte[] bytesAsObject = ArrayUtils.toObject(bytes);
        recipeService.saveImage(id, bytesAsObject);
        assertArrayEquals(bytesAsObject, recipe.getImage());
    }
}