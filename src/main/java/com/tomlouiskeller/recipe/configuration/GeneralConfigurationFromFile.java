package com.tomlouiskeller.recipe.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class GeneralConfigurationFromFile implements GeneralConfiguration {

    @Value("${recipes.quickRecipes.MaxDuration}")
    private Integer quickRecipesMaxDuration;

    @Value("${spring.profiles.active:default}") // If spring.profiles.active is set => use it - else => use 'default'
    private String springProfile;

}
