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

    @Value("${spring.config.additional-location}")
    private String additionalLocation;

    // Testing additional-location is just here to make sure we have the standard application configuration file //

    @Test
    public void additionalLocationIsNotNull() {
        assertNotNull(additionalLocation);
    }
}