package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurement, Long> {

    Optional<UnitOfMeasurement> findByName(String name);
}