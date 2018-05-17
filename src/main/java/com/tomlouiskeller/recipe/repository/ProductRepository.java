package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}