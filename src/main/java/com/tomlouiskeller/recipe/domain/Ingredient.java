package com.tomlouiskeller.recipe.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Comparator;

@Setter
@Getter
public class Ingredient implements Comparable<Ingredient>, Comparator<Ingredient> {

    @Id
    private String id;
    private Double amount;

    // Relations
    @DBRef
    private UnitOfMeasurement unitOfMeasurement;
    @DBRef
    private Product product;

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
