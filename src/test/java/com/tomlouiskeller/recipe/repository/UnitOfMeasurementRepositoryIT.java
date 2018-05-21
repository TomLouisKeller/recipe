package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasurementRepositoryIT {

    @Autowired
    private UnitOfMeasurementRepository unitOfMeasurementRepository;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByName() {
        String expectedName = "Teaspoon";
        Optional<UnitOfMeasurement> uomOptional = unitOfMeasurementRepository.findByName(expectedName);

        assertEquals(expectedName, uomOptional.get().getName());
    }

    @Test
    public void findByNameDash() {
        String expectedName = "Dash";
        Optional<UnitOfMeasurement> uomOptional = unitOfMeasurementRepository.findByName(expectedName);

        assertEquals(expectedName, uomOptional.get().getName());
    }
}