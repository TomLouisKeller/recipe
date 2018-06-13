package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> findAll(){
        return new TreeSet<Recipe>(recipeRepository.findAllByOrderByTitle());
    }

//    public Set<Recipe> findQuickRecipes(Integer maxDuration){
//        Set<Recipe> recipeSet = new HashSet<>();
//        Iterable<Recipe> recipeIterable = recipeRepository.findRecipesQuickerThan(maxDuration);
//        recipeIterable.forEach(recipeSet::add);
//        return recipeSet;
//    }

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Set<Recipe> saveAll(Iterable<Recipe> recipes){
        Set<Recipe> recipeSet = new HashSet<>();
        Iterable<Recipe> recipeIterable = recipeRepository.saveAll(recipes);
        recipeIterable.forEach(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.orElseThrow(() -> new RecipeNotFoundException("Recipe with ID " + id + " was not found."));
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void saveImage(String id, Byte[] bytes) {
        Recipe recipe = this.findById(id);
        recipe.setImage(bytes);
        this.save(recipe);
    }

    @Override
    public Long count() {
        return recipeRepository.count();
    }

}
