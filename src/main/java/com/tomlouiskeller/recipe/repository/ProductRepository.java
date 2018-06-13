package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {

    Optional<Product> findByName(String name);
}