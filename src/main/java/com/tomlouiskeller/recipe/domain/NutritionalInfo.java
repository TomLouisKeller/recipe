package com.tomlouiskeller.recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Data
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
