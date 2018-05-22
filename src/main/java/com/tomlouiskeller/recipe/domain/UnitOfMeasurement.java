package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude="ingredients")
@ToString(exclude = "ingredients")
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

}
