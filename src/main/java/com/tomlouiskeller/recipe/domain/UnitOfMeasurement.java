package com.tomlouiskeller.recipe.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UnitOfMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relations
    @OneToMany(mappedBy = "unitOfMeasurement", fetch = FetchType.LAZY) // Could think about cascade = CascadeType.ALL
    private Set<Ingredient> ingredients;

    public UnitOfMeasurement() {
    }

    public UnitOfMeasurement(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
