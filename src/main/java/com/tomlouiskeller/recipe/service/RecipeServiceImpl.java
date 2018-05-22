package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> findAll(){
        return new HashSet<>(recipeRepository.findAll());
    }

    public Set<Recipe> findQuickRecipes(Integer maxDuration){
        Set<Recipe> recipeSet = new HashSet<>();
        Iterable<Recipe> recipes = recipeRepository.findByPreparationDurationPlusCookingDurationIsLessThanEqual(maxDuration);
        recipes.iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Set<Recipe> saveAll(Iterable<Recipe> recipes){
        List<Recipe> recipesList = recipeRepository.saveAll(recipes);
        return new HashSet<>(recipesList);
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        RecipeNotFoundException recipeNotFoundException = new RecipeNotFoundException("Recipe with ID " + id + " not found.");
        return optionalRecipe.orElseThrow(() -> recipeNotFoundException);
    }

}
