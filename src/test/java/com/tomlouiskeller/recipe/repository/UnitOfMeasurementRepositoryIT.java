package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitOfMeasurementRepositoryIT {

    @Autowired
    private UnitOfMeasurementRepository unitOfMeasurementRepository;

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