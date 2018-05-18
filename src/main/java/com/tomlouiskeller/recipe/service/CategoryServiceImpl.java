package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // TODO: We have this findByNameOrCreate thing in 3 classes. Find a way to sort this out.
    // categoryRepository.findByName(name).orElse(create(name)) doesn't work because it evaluates it.
    public Category findByNameOrCreate(String name){
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent())
            return category.get();
        else
            return create(name);
    }

    private Category create(String name) {
        Category category;
        category = new Category(name);
        categoryRepository.save(category);
        return category;
    }
}
