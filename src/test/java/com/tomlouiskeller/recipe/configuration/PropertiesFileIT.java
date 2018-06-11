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

    @Value("${recipes.quickRecipes.MaxDuration}")
    private Integer maxDuration;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.datasource.platform}")
    private String platform;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    // Tests for Profile

    @Test
    public void profileIsAvailable() {
        assertNotNull(profile);
    }

    @Test // Make sure the property profile is set to either development or production
    public void profileIsDevelopmentOrProduction() {
        boolean isH2 = platform.matches("(?i:.*h2.*)");
        boolean isMysql = platform.matches("(?i:.*mysql.*)");
        assertTrue(isH2 || isMysql);
    }

    // Tests for Platform

    @Test
    public void platformIsAvailable() {
        assertNotNull(platform);
    }

    @Test // Make sure the property profile is set to either development or production
    public void platformIsDevelopmentOrProduction() {
        boolean isH2 = platform.matches("(?i:.*h2.*)");
        boolean isMysql = platform.matches("(?i:.*mysql.*)");
        assertTrue(isH2 || isMysql);
    }


    // Tests for datasourceUrl

//    @Test
//    public void datasourceUrlIsAvailable() {
//        assertNotNull(datasourceUrl);
//    }
//
//    @Test // Make sure the property profile is set to either development or production
//    public void datasourceUrlWut() {
//        System.out.println(datasourceUrl);
//    }
}