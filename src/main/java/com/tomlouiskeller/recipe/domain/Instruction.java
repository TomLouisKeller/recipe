package com.tomlouiskeller.recipe.domain;


import lombok.Data;

import javax.persistence.*;

@Data
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
