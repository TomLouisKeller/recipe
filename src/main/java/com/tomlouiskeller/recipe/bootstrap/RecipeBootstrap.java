package com.tomlouiskeller.recipe.bootstrap;

import com.tomlouiskeller.recipe.domain.*;
import com.tomlouiskeller.recipe.repository.RecipeRepository;
import com.tomlouiskeller.recipe.repository.UnitOfMeasurementRepository;
import com.tomlouiskeller.recipe.service.CategoryService;
import com.tomlouiskeller.recipe.service.IngredientService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryService categoryService;
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;

    public RecipeBootstrap(CategoryService categoryService, RecipeRepository recipeRepository, IngredientService ingredientService, UnitOfMeasurementRepository unitOfMeasurementRepository) {
        this.categoryService = categoryService;
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getAdditionalRecipes());
        //log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> getAdditionalRecipes() {
        List<Recipe> recipes = new ArrayList<>(1);

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        recipeRepository.save(guacRecipe); // Have to save it to reference it in the ingredients
        guacRecipe.setTitle("Perfect Guacamole");
        guacRecipe.setPreparationDuration(10);
        guacRecipe.setCookingDuration(0);
        guacRecipe.setServings(4);
        guacRecipe.setDifficulty(Difficulty.AMATEUR);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");

        guacRecipe.setInstruction(new Instruction("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd"));

        guacRecipe.setNutritionalInfo(new NutritionalInfo("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws"));

        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "", "ripe avocados", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(0.5d, "Teaspoon", "Kosher salt", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "fresh lime juice or lemon juice", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "minced red onion or thinly sliced green onion", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "", "serrano chiles, stems and seeds removed, minced", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "Cilantro", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(2d, "Dash", "freshly grated black pepper", guacRecipe));
        guacRecipe.addIngredient(ingredientService.ingredientFactory(0.5d, "", "ripe tomato, seeds and pulp removed, chopped", guacRecipe));

        Category mexican = categoryService.findByNameOrCreate("Mexican");
        Category american = categoryService.findByNameOrCreate("American");

        guacRecipe.getCategories().add(mexican);
        guacRecipe.getCategories().add(american);

        //add to return list
        recipes.add(guacRecipe);

        return recipes;
    }
}