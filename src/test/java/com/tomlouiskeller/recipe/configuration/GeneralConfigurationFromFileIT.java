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
public class GeneralConfigurationFromFileIT {


    @Autowired
    private GeneralConfigurationFromFile generalConfigurationFromFile;

    @Test
    public void getQuickRecipesMaxDurationNotNull() {
        Integer quickRecipesMaxDuration = generalConfigurationFromFile.getQuickRecipesMaxDuration();
        assertNotNull(quickRecipesMaxDuration);
    }

    @Test
    public void getQuickRecipesMaxDurationPositive() {
        Integer quickRecipesMaxDuration = generalConfigurationFromFile.getQuickRecipesMaxDuration();
        assertTrue(quickRecipesMaxDuration > 0);
    }

    @Test
    public void profileIsAvailable() {
        String profile = generalConfigurationFromFile.getSpringProfile();
        assertNotNull(profile);
    }

    @Test // Make sure the property profile is set to either development or production
    public void profileIsDevelopmentOrProduction() {
        String profile = generalConfigurationFromFile.getSpringProfile();
        boolean isDefault = profile.matches("(?i:default)");
        boolean isDevelH2 = profile.matches("(?i:devel-h2)");
        boolean isDevelMySql = profile.matches("(?i:devel-mysql)");
        boolean isProdMySql = profile.matches("(?i:prod-mysql)");
        assertTrue(isDefault || isDevelH2 || isDevelMySql || isProdMySql);
    }

}