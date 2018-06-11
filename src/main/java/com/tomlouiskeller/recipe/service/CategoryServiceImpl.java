package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public Category findOrCreate(String name){
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if (optionalCategory.isPresent())
            return optionalCategory.get();
        else {
            Category category;
            category = new Category(name);
            categoryRepository.save(category);
            return category;
        }
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    @Override
    public SortedSet<Category> findAll() {
        return new TreeSet<>(categoryRepository.findAllByOrderByName());
    }

    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }
}
