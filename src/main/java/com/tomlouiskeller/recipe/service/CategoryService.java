package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;

public interface CategoryService {
    /**
     * Returns category based on name.
     * Is never null
     * @param name, Name of category which is to be found
     * @return Category, Category that was either found in persistence layer(db) or created therein
     */
    Category getByName(String name);
}
