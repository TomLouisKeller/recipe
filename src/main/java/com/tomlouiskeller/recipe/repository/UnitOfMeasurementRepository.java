package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, String> {

    Optional<UnitOfMeasurement> findByName(String name);
}