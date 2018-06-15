package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UnitOfMeasurementRepository extends MongoRepository<UnitOfMeasurement, String> {

    Optional<UnitOfMeasurement> findByName(String name);
}