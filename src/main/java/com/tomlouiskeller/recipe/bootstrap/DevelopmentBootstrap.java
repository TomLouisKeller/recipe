package com.tomlouiskeller.recipe.bootstrap;

import com.tomlouiskeller.recipe.domain.Difficulty;
import com.tomlouiskeller.recipe.domain.Instruction;
import com.tomlouiskeller.recipe.domain.NutritionalInfo;
import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.CategoryService;
import com.tomlouiskeller.recipe.service.IngredientService;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Profile({"default", "devel-mysql", "devel-h2"})
@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BasicBootstrap basicBootstrap;
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private CategoryService categoryService;

    public DevelopmentBootstrap(BasicBootstrap basicBootstrap, RecipeService recipeService, IngredientService ingredientService, CategoryService categoryService) {
        this.basicBootstrap = basicBootstrap;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Bootstrapping Data");

        basicBootstrap.initCategories();
        basicBootstrap.initUnitOfMeasurements();

        if (recipeService.count() == 0L){
            List<Recipe> recipes = new ArrayList<>(3);
            recipes.add(createGuacamole());
            recipes.add(createRoesti());
            recipes.add(createSpaghetti());
            recipeService.saveAll(recipes);
        }
    }

    private Recipe createGuacamole() {
        Recipe guacamole = Recipe.builder()
                .title("Perfect Guacamole")
                .preparationDuration(10)
                .cookingDuration(0)
                .servings(4)
                .difficulty(Difficulty.EASY)
                .source("Simply Recipes")
                .url("http://www.simplyrecipes.com/recipes/perfect_guacamole/")
                .instruction(new Instruction("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
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
                        "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd"))
                .nutritionalInfo(new NutritionalInfo("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                        "\n" +
                        "\n" +
                        "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws"))
                .build();

        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "", "ripe avocados"));
        guacamole.addIngredient(ingredientService.ingredientFactory(0.5d, "Teaspoon", "Kosher salt"));
        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "fresh lime juice or lemon juice"));
        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "minced red onion or thinly sliced green onion"));
        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "", "serrano chiles, stems and seeds removed, minced"));
        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "Cilantro"));
        guacamole.addIngredient(ingredientService.ingredientFactory(2d, "Dash", "freshly grated black pepper"));
        guacamole.addIngredient(ingredientService.ingredientFactory(0.5d, "", "ripe tomato, seeds and pulp removed, chopped"));
        guacamole.getCategories().add(categoryService.findOrCreate("Mexican"));
        guacamole.getCategories().add(categoryService.findOrCreate("American"));
        return guacamole;
    }

    private Recipe createRoesti() {
        Recipe roesti = Recipe.builder()
                .title("Rösti")
                .preparationDuration(5)
                .cookingDuration(30)
                .servings(4)
                .difficulty(Difficulty.EASY)
                .source("https://www.bettybossi.ch/")
                .url("https://www.bettybossi.ch/de/Rezept/ShowRezept/BB_CHCH090801_0107A-40-de")
                .instruction(new Instruction("1. Grate potatoes on the raffle in a bowl. 2. Add salt to grated potatoes. 3. Melt margarine in a frying pan. Add potatoes, fry with occasional turning for about 5 minutes.Use a scoop to form the rösti into a cake, don't move any further. Roast on medium heat for about 15 minutes. Put a flat plate on the pan and top with the rösti. Put a little bit of butter in the pan, let the rösti slide back into the pan, open fry for about 15 minutes."))
                .nutritionalInfo(new NutritionalInfo("Very light. Eat all you want."))
                .build();

        roesti.addIngredient(ingredientService.ingredientFactory(1d, "Kilogram", "Potatoes"));
        roesti.addIngredient(ingredientService.ingredientFactory(1d, "Teaspoon", "Kilogram"));
        roesti.addIngredient(ingredientService.ingredientFactory(null, "Some", "Margarine"));

        roesti.getCategories().add(categoryService.findOrCreate("Vegan"));
        roesti.getCategories().add(categoryService.findOrCreate("Swiss"));
        return roesti;
    }

    private Recipe createSpaghetti() {
        Recipe spaghetti = Recipe.builder()
                .title("Spaghetti with Tomato Sauce")
                .preparationDuration(7)
                .cookingDuration(11)
                .servings(4)
                .difficulty(Difficulty.MODERATE)
                .source("Grandma")
                .url("")
                .instruction(new Instruction("1. Bring water to a boil. 2. Add Spaghetti. 3. Cook for 11 minutes. 4. Add tomato sauce. 5. Heat up until everything is warm."))
                .nutritionalInfo(new NutritionalInfo("Eat to much and you'll be fat"))
                .build();

        spaghetti.addIngredient(ingredientService.ingredientFactory(300d, "Gram", "Spaghetti"));
        spaghetti.addIngredient(ingredientService.ingredientFactory(2d, "Teaspoon", "Salt"));
        spaghetti.addIngredient(ingredientService.ingredientFactory(1d, "Can", "Tomato Sauce"));

        spaghetti.getCategories().add(categoryService.findOrCreate("Vegetarian"));
        spaghetti.getCategories().add(categoryService.findOrCreate("Italian"));
        return spaghetti;
    }
}
