package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.UnitOfMeasurement;
import com.tomlouiskeller.recipe.repository.UnitOfMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitOfMeasurementServiceImpl implements UnitOfMeasurementService {

    private final UnitOfMeasurementRepository unitOfMeasurementRepository;

    public UnitOfMeasurementServiceImpl(UnitOfMeasurementRepository unitOfMeasurementRepository) {
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
    }

    // TODO: We have this getByName thing in 3 classes. Find a way to sort this out.
    public UnitOfMeasurement getByName(String name){
        Optional<UnitOfMeasurement> unitOfMeasurement = unitOfMeasurementRepository.findByName(name);
        if (unitOfMeasurement.isPresent())
            return unitOfMeasurement.get();
        else
            return create(name);
    }

    private UnitOfMeasurement create(String name) {
        UnitOfMeasurement uom;
        uom = new UnitOfMeasurement(name);
        unitOfMeasurementRepository.save(uom);
        return uom;
    }
}