package com.tomlouiskeller.recipe.domain;


import javax.persistence.*;

@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String text;

    public Instruction() {
    }

    public Instruction(String text) {
        this.text = text;
    }

    // Relations
    @OneToOne(mappedBy = "instruction")
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
