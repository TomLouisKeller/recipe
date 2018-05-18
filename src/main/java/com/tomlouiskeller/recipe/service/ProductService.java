package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Product;

public interface ProductService {
    /**
     * Returns product based on name.
     * Is never null
     * @param name, Name of product
     * @return Product, never null
     */
    Product getByName(String name);
}
