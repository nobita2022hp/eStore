package com.estore.repositories;

import com.estore.domain.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(Integer id);
    List<Category> findAll();
    Category create(Category entity);
    void update(Category entity);
    Category delete(Integer id);
}
