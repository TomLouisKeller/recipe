package com.tomlouiskeller.recipe.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;

@Setter
@Getter
@EqualsAndHashCode
@Document
public class Category implements Comparable<Category> {

    @Id
    private String id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Category category) {
        Comparator<Category> lambdaNameComparator = Comparator.comparing(Category::getName);
        return lambdaNameComparator.compare(this, category);
    }
}
