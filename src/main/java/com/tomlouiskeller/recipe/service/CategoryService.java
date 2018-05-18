package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;

public interface CategoryService {

    Category findByNameOrCreate(String name);
}
