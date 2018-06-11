package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeRepositoryIT {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void findByName() {
        String expectedTitle = "Rösti";
        Optional<Recipe> recipeOptional = recipeRepository.findByTitle(expectedTitle);

        assertEquals(expectedTitle, recipeOptional.get().getTitle());
    }

}