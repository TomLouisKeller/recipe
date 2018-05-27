package com.tomlouiskeller.recipe.domain;

import lombok.Getter;

@Getter
public enum Difficulty {
    EASY("Easy"),
    MODERATE("Moderate"),
    HARD("Hard");

    private String readable;

    Difficulty(String readable) {
        this.readable = readable;
    }
}