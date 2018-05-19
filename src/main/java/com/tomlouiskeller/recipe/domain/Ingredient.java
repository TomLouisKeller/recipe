package com.tomlouiskeller.recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    // Relations
    @ManyToOne(fetch = FetchType.EAGER) // TODO: Is fetch=EAGER necessary
    private UnitOfMeasurement unitOfMeasurement;
    @ManyToOne(fetch = FetchType.EAGER) // TODO: Is fetch=EAGER necessary
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER) // TODO: Is fetch=EAGER necessary
    private Recipe recipe;


    public Ingredient() {
    }

    public Ingredient(Double amount, UnitOfMeasurement unitOfMeasurement, Product product) {
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
        this.product = product;
    }

}
