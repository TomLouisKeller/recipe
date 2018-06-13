package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class NutritionalInfo {

    @Id
    private String id;
    private String text;

    public NutritionalInfo(String text) {
        this.text = text;
    }

    public NutritionalInfo(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
