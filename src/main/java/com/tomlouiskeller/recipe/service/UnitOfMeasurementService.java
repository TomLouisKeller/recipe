package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;

public interface UnitOfMeasurementService {
    /**
     * Returns unit based on name.
     * Is never null
     * @param name, Name of unit
     * @return UnitOfMeasurement, never null
     */
    UnitOfMeasurement getByName(String name);
}
