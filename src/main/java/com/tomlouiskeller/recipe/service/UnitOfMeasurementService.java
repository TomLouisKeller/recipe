package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;

public interface UnitOfMeasurementService {
    UnitOfMeasurement findByNameOrCreate(String name);
}
