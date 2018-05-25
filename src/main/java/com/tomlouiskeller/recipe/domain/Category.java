package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude="recipes")
@ToString(exclude = "recipes")
@Entity
public class Category implements Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relations
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Category category) {
        Comparator<Category> lambdaNameComparator = Comparator.comparing(Category::getName);
        return lambdaNameComparator.compare(this, category);
    }
}
