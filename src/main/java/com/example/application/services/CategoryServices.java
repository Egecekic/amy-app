package com.example.application.services;

import com.example.application.models.Category;

import java.util.Set;

public interface CategoryServices {
    Set<Category> getList();
    Category save(Category c);
}
