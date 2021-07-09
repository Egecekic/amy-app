package com.example.application.Repositories;

import com.example.application.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoryRespostpries extends CrudRepository<Category,Long> {
    List<Category> findByName(String c);
}
