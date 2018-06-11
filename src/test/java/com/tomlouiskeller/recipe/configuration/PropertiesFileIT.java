package com.tomlouiskeller.recipe.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// This file is here to test if we have access to the properties file
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesFileIT {

    @Value("${spring.datasource.platform}")
    private String platform;

    // Tests for Platform //

    @Test
    public void platformIsAvailable() {
        assertNotNull(platform);
    }

    @Test // Make sure the property profile is set to either development or production
    public void platformIsDevelopmentOrProduction() {
        boolean isH2 = platform.matches("(?i:h2)");
        boolean isMysql = platform.matches("(?i:mysql)");
        assertTrue(isH2 || isMysql);
    }
}