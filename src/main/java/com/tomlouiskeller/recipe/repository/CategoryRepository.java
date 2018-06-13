package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.SortedSet;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByName(String name);

    SortedSet<Category> findAllByOrderByName();
}
