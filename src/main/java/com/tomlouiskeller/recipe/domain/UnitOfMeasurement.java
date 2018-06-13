package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UnitOfMeasurement {

    @Id
    private String id;
    private String name;

    public UnitOfMeasurement(String name) {
        this.name = name;
    }

}
