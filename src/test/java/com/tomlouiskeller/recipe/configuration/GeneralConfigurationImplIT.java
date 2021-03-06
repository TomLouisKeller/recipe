package com.tomlouiskeller.recipe.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralConfigurationImplIT {


    @Autowired
    private GeneralConfigurationImpl generalConfiguration;

    @Test
    public void getQuickRecipesMaxDurationNotNull() {
        Integer quickRecipesMaxDuration = generalConfiguration.getQuickRecipesMaxDuration();
        assertNotNull(quickRecipesMaxDuration);
    }

    @Test
    public void getQuickRecipesMaxDurationPositive() {
        Integer quickRecipesMaxDuration = generalConfiguration.getQuickRecipesMaxDuration();
        assertTrue(quickRecipesMaxDuration > 0);
    }

    @Test
    public void profileIsAvailable() {
        String profile = generalConfiguration.getSpringProfile();
        assertNotNull(profile);
    }

    @Test // Make sure the property profile is set to either development or production
    public void profileIsSetCorrect() {
        String profile = generalConfiguration.getSpringProfile();
        boolean isDefault = profile.matches("(?i:default)");
        boolean isDevelopment = profile.matches("(?i:development)");
        boolean isProduction = profile.matches("(?i:production)");
        assertTrue(isDefault || isDevelopment || isProduction);
    }

}