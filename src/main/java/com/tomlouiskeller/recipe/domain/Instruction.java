package com.tomlouiskeller.recipe.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
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
