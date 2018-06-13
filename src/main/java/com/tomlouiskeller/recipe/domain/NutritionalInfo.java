package com.tomlouiskeller.recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
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
