package com.tomlouiskeller.recipe.exception;

import java.util.NoSuchElementException;

public class RecipeNotFoundException extends NoSuchElementException {

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
