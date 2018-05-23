package com.tomlouiskeller.recipe.form;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


// TODO: How can we enforce the annotations?
// TODO: Finish these tests
public class RecipeFormTest {


    private RecipeForm recipeForm;

    @Before
    public void setUp() {
        recipeForm = new RecipeForm();
    }



    @Test // There needs to be a constructor without arguments
    public void noArgsConstructorTest() {
        assertNotNull(recipeForm);
    }


    @Test // recipe.id can be null
    public void setRecipeId() {
        recipeForm.setRecipeId(null);
    }

    @Test
    public void setRecipeTitle() {
        recipeForm.setRecipeTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas cursus rhoncus dignissim. Nulla lacinia ac justo vel auctor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean metus nibh, malesuada mollis quam vulputate, finibus rutrum magna. Curabitur convallis arcu non pellentesque ultricies. Donec sit amet vestibulum magna. Integer eu orci ex. Nunc porttitor viverra libero a malesuada.\n" +
                "\n" +
                "Etiam elementum elementum dolor, at porta mi vehicula at. Nunc venenatis eu metus porttitor aliquam. Sed quam neque, venenatis at ultrices quis, fermentum vitae erat. Praesent et auctor eros. Duis sed turpis scelerisque, maximus augue quis, iaculis nunc. Praesent scelerisque metus eros, ac posuere urna auctor non. Mauris id posuere lectus. Nulla sit amet ullamcorper nulla.\n" +
                "\n" +
                "Vivamus fringilla dui purus, in sodales arcu condimentum ac. Sed non mauris erat. Etiam erat felis, bibendum ornare mollis eget, vulputate at mi. Donec sed cursus lacus. Nam fringilla quam tellus, quis pulvinar neque imperdiet vitae. Donec eget sapien ante. Vivamus a ullamcorper sem, at egestas leo. Proin ante ex, condimentum quis volutpat a, hendrerit ut mauris.\n" +
                "\n" +
                "Duis ac velit ullamcorper, facilisis diam non, sodales lacus. Quisque a tortor pellentesque, tincidunt elit non, convallis odio. Donec eu odio consequat, aliquet sem sit amet, vehicula dui. Vivamus viverra mollis lacinia. Donec varius quis nulla id iaculis. In semper venenatis ex sit amet ornare. Proin vitae consectetur ex. Morbi bibendum urna non sagittis iaculis. Fusce varius risus at quam rutrum lobortis. Proin urna mi, consectetur ut elementum in, hendrerit quis sapien. Phasellus dui diam, interdum non pulvinar eu, laoreet ut turpis. Suspendisse mollis est sollicitudin, hendrerit mauris vitae, imperdiet sapien. Curabitur tempor urna ut dictum porttitor. Duis non blandit nisi.\n" +
                "\n" +
                "Vestibulum in augue eleifend, elementum orci in, efficitur nisi. Aenean venenatis dui nec fermentum ultrices. Proin et congue diam. Duis at luctus enim, a lacinia odio. Praesent nec pulvinar quam. Integer condimentum, augue sed iaculis consectetur, tortor felis dictum mi, nec consectetur purus erat nec lacus. Nam tempus sed sapien et lacinia. Maecenas dapibus interdum blandit. In eu mauris fermentum, volutpat mauris in, venenatis lorem. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras in egestas orci, nec molestie leo. Vivamus sit amet justo et dolor congue commodo eu eu tellus. Vivamus quis tellus in ante sodales accumsan.");
    }

    @Test
    public void setRecipePreparationDuration() {
    }

    @Test
    public void setRecipeCookingDuration() {
    }

    @Test
    public void setRecipeServings() {
    }

    @Test
    public void setRecipeSource() {
    }

    @Test
    public void setRecipeUrl() {
    }

    @Test
    public void setRecipeImage() {
    }

    @Test
    public void setRecipeDifficulty() {
    }

    @Test
    public void setInstructionText() {
    }

    @Test
    public void setNutritionalInfoText() {
    }
}