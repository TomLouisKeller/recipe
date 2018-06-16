package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.*;
import com.tomlouiskeller.recipe.form.RecipeForm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// TODO: convert image both ways

// These tests also test Recipe and RecipeForm and are therefore not pure Unit-Tests
// This is wanted.
// We do mock IngredientServiceImpl
public class RecipeFormServiceImplIT {

    private RecipeFormServiceImpl recipeFormService;

    @Mock
    private IngredientServiceImpl ingredientService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeFormService = new RecipeFormServiceImpl(ingredientService);
    }

    // --- Convert RecipeForm to Recipe --- //

    @Test
    public void convertFormToEntityIsNotNull() {
        RecipeForm recipeForm = mock(RecipeForm.class);
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertNotNull(recipe);
    }


    // TODO: Check id
    // TODO: Move recipeInstruction and nutritionalInfoText out
    // TODO: Test instructionId and nutritionalId
    // TODO: Rename convertFormToEntityStandardFields to convertFormToEntityRecipeFields
    @Test
    public void convertFormToEntityStandardFields() {
        String recipeTitle = "Pizza";
        Integer recipePreparationDuration = 30;
        Integer recipeCookingDuration = 60;
        Integer recipeServings = 4;
        String recipeSource = "yaaa";
        String recipeUrl = "yaaa.com/pizza";
        Difficulty recipeDifficulty = Difficulty.EASY;
        String instruction = "recipeInstruction";
        List<Ingredient> recipeIngredients = Collections.emptyList();
        RecipeForm recipeForm = RecipeForm.builder()
                                .recipeTitle(recipeTitle)
                                .recipePreparationDuration(recipePreparationDuration)
                                .recipeCookingDuration(recipeCookingDuration)
                                .recipeServings(recipeServings)
                                .recipeSource(recipeSource)
                                .recipeUrl(recipeUrl)
                                .recipeDifficulty(recipeDifficulty)
                                .recipeInstruction(instruction)
                                .recipeIngredients(recipeIngredients)
                                .build();

        Recipe recipe = recipeFormService.convert(recipeForm);

        assertEquals(recipeTitle, recipe.getTitle());
        assertEquals(recipePreparationDuration, recipe.getPreparationDuration());
        assertEquals(recipeCookingDuration, recipe.getCookingDuration());
        assertEquals(recipeServings, recipe.getServings());
        assertEquals(recipeSource, recipe.getSource());
        assertEquals(recipeUrl, recipe.getUrl());
        assertEquals(recipeDifficulty, recipe.getDifficulty());
        assertEquals(instruction, recipe.getInstruction());
    }

    @Test
    public void convertToRecipeTestIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        double potatoAmount = 1d;
        String potatoUom ="Kilogram";
        String potatoProduct = "Potatoes";
        Ingredient potatoes = new Ingredient(potatoAmount,  new UnitOfMeasurement(potatoUom), new Product(potatoProduct));

        double saltAmount = 3d;
        String saltUom = "Teaspoon";
        String saltProduct = "Salt";
        Ingredient salt = new Ingredient(saltAmount, new UnitOfMeasurement(saltUom), new Product(saltProduct));

        ingredients.add(potatoes);
        ingredients.add(salt);

        RecipeForm recipeForm = RecipeForm.builder()
                .recipeIngredients(ingredients)
                .build();

        when(ingredientService.ingredientFactory(potatoAmount, potatoUom, potatoProduct)).thenReturn(potatoes);
        when(ingredientService.ingredientFactory(saltAmount, saltUom, saltProduct)).thenReturn(salt);

        Recipe recipe = recipeFormService.convert(recipeForm);

        SortedSet<Ingredient> actual = recipe.getIngredients();
        assertArrayEquals(ingredients.toArray(), actual.toArray());
    }

    @Test
    public void convertToRecipeTestCategories() {

        Category american = new Category("American");
        Category swiss = new Category("Swiss");
        Set<Category> recipeCategories = new HashSet<>(Arrays.asList(american, swiss));
        RecipeForm recipeForm = RecipeForm.builder()
                .recipeCategories(recipeCategories)
                .recipeIngredients(Collections.emptyList())
                .build();
        Recipe recipe = recipeFormService.convert(recipeForm);
        assertEquals(recipeCategories, recipe.getCategories());

    }

    // --- Convert Recipe to RecipeForm --- //

    @Test
    public void convertEntityToFormIsNotNull() {
        Recipe recipe = mock(Recipe.class);
        RecipeForm recipeForm = recipeFormService.convert(recipe, null);
        assertNotNull(recipeForm);
    }

    @Test
    public void convertEntityToFormRecipeFields() {
        String recipeTitle = "Pizza";
        Integer recipePreparationDuration = 30;
        Integer recipeCookingDuration = 60;
        Integer recipeServings = 4;
        String recipeSource = "yaaa";
        String recipeUrl = "yaaa.com/pizza";
        String instruction = "instruction";
        Difficulty recipeDifficulty = Difficulty.EASY;

        Recipe recipe = Recipe.builder()
                .title(recipeTitle)
                .preparationDuration(recipePreparationDuration)
                .cookingDuration(recipeCookingDuration)
                .servings(recipeServings)
                .source(recipeSource)
                .url(recipeUrl)
                .difficulty(recipeDifficulty)
                .instruction(instruction)
                .build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);

        assertEquals(recipeTitle, recipeForm.getRecipeTitle());
        assertEquals(recipePreparationDuration, recipeForm.getRecipePreparationDuration());
        assertEquals(recipeCookingDuration, recipeForm.getRecipeCookingDuration());
        assertEquals(recipeServings, recipeForm.getRecipeServings());
        assertEquals(recipeSource, recipeForm.getRecipeSource());
        assertEquals(recipeUrl, recipeForm.getRecipeUrl());
        assertEquals(recipeDifficulty, recipeForm.getRecipeDifficulty());
    }

    @Test
    public void convertToRecipeFormTestIngredients() {
        SortedSet<Ingredient> ingredients = new TreeSet<>();
        ingredients.add(mock(Ingredient.class));
        ingredients.add(mock(Ingredient.class));

        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredients);
        RecipeForm recipeForm = recipeFormService.convert(recipe, null);

        List<Ingredient> actual = recipeForm.getRecipeIngredients();

        assertArrayEquals(ingredients.toArray(), actual.toArray());
    }

    @Test
    public void convertEntityToFormRecipeCategories() {

        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        List<Category> categoryList = Arrays.asList(category1, category2);
        Set<Category> categorySet = new HashSet<>(categoryList);

        Recipe recipe = new Recipe();
        recipe.setCategories(categorySet);

        RecipeForm recipeForm = recipeFormService.convert(recipe, null);
        assertEquals(categorySet, recipeForm.getRecipeCategories());
    }

    @Test
    public void convertEntityToFormAvailableCategories() {
        SortedSet<Category> categorySet = new TreeSet<>();
        categorySet.add(mock(Category.class));
        categorySet.add(mock(Category.class));

        Recipe recipe = Recipe.builder().build();

        RecipeForm recipeForm = recipeFormService.convert(recipe, categorySet);
        SortedSet<Category> actual = recipeForm.getAvailableCategories();
        assertArrayEquals(categorySet.toArray(), actual.toArray());
    }
}