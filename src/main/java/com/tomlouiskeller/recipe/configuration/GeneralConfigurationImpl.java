package com.tomlouiskeller.recipe.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@Data
@Configuration
public class GeneralConfigurationImpl implements GeneralConfiguration {

    private ConfigurableEnvironment configurableEnvironment;

    public GeneralConfigurationImpl(ConfigurableEnvironment configurableEnvironment) {
        this.configurableEnvironment = configurableEnvironment;
    }

    @Override
    public Integer getQuickRecipesMaxDuration() {
        String propertyName = "recipes.quickRecipes.MaxDuration";
        String maxDurationString = configurableEnvironment.getProperty(propertyName);
        Integer maxDuration = Integer.parseInt(maxDurationString);
        return maxDuration;
    }

    @Override
    public String getSpringProfile() {
        log.info("Got Profile from new configuration class");
        String[] activeProfiles = configurableEnvironment.getActiveProfiles();
        if(activeProfiles.length == 1)
            return activeProfiles[0];
        else if(activeProfiles.length == 0)
            return "default";
        else
            throw new RuntimeException("More than one profile active.");
    }
}
