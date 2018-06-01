package com.tomlouiskeller.recipe.repository;

import com.tomlouiskeller.recipe.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.SortedSet;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    SortedSet<Category> findAllByOrderByName();
}
