package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Product;

public interface ProductService {
    Product findByNameOrCreate(String name);
}
