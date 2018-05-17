package com.tomlouiskeller.recipe.domain;

import javax.persistence.*;

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
