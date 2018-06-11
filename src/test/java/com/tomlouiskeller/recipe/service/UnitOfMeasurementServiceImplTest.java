package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import com.tomlouiskeller.recipe.repository.UnitOfMeasurementRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitOfMeasurementServiceImplTest {

    private UnitOfMeasurementServiceImpl unitOfMeasurementService;

    @Mock
    private UnitOfMeasurementRepository unitOfMeasurementRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasurementService = new UnitOfMeasurementServiceImpl(unitOfMeasurementRepository);
    }

    @Test
    public void getByNameReturnsUnitOfMeasurement() {
        String find = "Tablespoon";
        UnitOfMeasurement actual = unitOfMeasurementService.getByName(find);
        assertNotNull(actual);
        assertEquals(UnitOfMeasurement.class, actual.getClass());
    }

    // TODO: More findOrCreate tests

}