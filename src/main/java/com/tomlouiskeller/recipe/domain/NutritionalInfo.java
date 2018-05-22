package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude="recipe")
@ToString(exclude = "recipe")
@Entity
public class NutritionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String text;

    // Relations
    @OneToOne(mappedBy = "nutritionalInfo")
    private Recipe recipe;

    public NutritionalInfo() {
    }

    public NutritionalInfo(String text) {
        this.text = text;
    }

}
