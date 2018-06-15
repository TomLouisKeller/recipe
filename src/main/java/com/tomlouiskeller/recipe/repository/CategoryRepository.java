package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.SortedSet;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findByName(String name);

    SortedSet<Category> findAllByOrderByName();
}
