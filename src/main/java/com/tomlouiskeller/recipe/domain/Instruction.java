package com.tomlouiskeller.recipe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude="recipe")
@ToString(exclude = "recipe")
@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String text;

    // Relations
    @OneToOne(mappedBy = "instruction")
    private Recipe recipe;

    public Instruction() {
    }

    public Instruction(String text) {
        this.text = text;
    }
}
