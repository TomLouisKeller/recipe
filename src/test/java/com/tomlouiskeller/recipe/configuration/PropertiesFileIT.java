package com.tomlouiskeller.recipe.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesFileIT {

    @Value("${environment}")
    private String environment;

    @Value("${recipes.quickRecipes.MaxDuration}")
    private Integer maxDuration;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.datasource.platform}")
    private String platform;

    // Tests for Environment

    @Test
    public void environmentIsSet() {
        assertNotNull(environment);
    }

    @Test // Make sure the property environment is set to either development or production
    public void environmentIsDevelopmentOrProduction() {
        boolean isDevelopment = environment.matches("(?i:.*development.*)");
        boolean isProduction = environment.matches("(?i:.*production.*)");
        assertTrue(isDevelopment || isProduction);
    }

    // Tests for Profile

    @Test
    public void profileIsAvailable() {
        assertNotNull(profile);
    }

    @Test // Make sure the property profile is set to either development or production
    public void profileIsDevelopmentOrProduction() {
        boolean isDevelopment = profile.matches("(?i:.*development.*)");
        boolean isProduction = profile.matches("(?i:.*production.*)");
        assertTrue(isDevelopment || isProduction);
    }

    // Tests for Platform

    @Test
    public void platformIsAvailable() {
        assertNotNull(platform);
    }

    @Test // Make sure the property profile is set to either development or production
    public void platformIsDevelopmentOrProduction() {
        boolean isDevelopment = platform.matches("(?i:.*development.*)");
        boolean isProduction = platform.matches("(?i:.*production.*)");
        assertTrue(isDevelopment || isProduction);
    }

}