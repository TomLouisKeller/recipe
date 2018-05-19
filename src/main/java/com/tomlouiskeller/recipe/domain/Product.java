package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude="ingredients")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relations
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // Could think about cascade = CascadeType.ALL
    private Set<Ingredient> ingredients;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

}
