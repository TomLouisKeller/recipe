package com.tomlouiskeller.recipe.domain;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
