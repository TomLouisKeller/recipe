package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> findAll(){
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.addAll(recipeRepository.findAll());
        return recipeSet;
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
        Set<Recipe> recipeSet = new HashSet<>();
        List<Recipe> recipesList = recipeRepository.saveAll(recipes);
        recipeSet.addAll(recipesList);
        return recipeSet;
    }



}
