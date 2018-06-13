package com.tomlouiskeller.recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
public class Instruction {

    @Id
    private String id;
    private String text;

    public Instruction(String text) {
        this.text = text;
    }

    public Instruction(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
