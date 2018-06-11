package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;

import java.util.List;
import java.util.SortedSet;

public interface CategoryService {
    /**
     * Returns category based on name.
     * Is never null
     * @param name, Name of category which is to be found
     * @return Category, Category that was either found in persistence layer(db) or created therein
     */
    Category findOrCreate(String name);

    SortedSet<Category> findAll();

    void saveAll(List<Category> categories);

    Long count();
}
