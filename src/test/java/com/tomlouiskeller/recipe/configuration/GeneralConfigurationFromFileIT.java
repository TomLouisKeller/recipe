package com.tomlouiskeller.recipe.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

    @Before
    public void setUp() {
    }

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

}