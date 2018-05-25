package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Comparator;

@Data
@Entity
@Slf4j
public class Ingredient implements Comparable<Ingredient>, Comparator<Ingredient> {
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

    public String getProductName() {
        return product.getName();
    }

    @Override // TODO: For now we sort by Product.name. Later we will sort by a dedicated sort field.
    public int compareTo(Ingredient ingredient) {
        return lambdaProductNameComparator.compare(this, ingredient);
    }

    @Override // Not going to write a new class for that.
    public int compare(Ingredient ingredient1, Ingredient ingredient2) {
        return lambdaProductNameComparator.compare(ingredient1, ingredient2);
    }

    public static Comparator<Ingredient> lambdaProductNameComparator = Comparator.comparing(Ingredient::getProductName);
}
