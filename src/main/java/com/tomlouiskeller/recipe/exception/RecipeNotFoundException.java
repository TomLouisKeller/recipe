package com.tomlouiskeller.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends NoSuchElementException {

    public RecipeNotFoundException(String message) {
        super(message);
    }
}