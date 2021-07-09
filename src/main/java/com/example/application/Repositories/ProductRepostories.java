package com.example.application.Repositories;

import com.example.application.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepostories extends CrudRepository<Product,Long>, JpaRepository<Product,Long> {
    Optional<Product> findAllById(Long aLong);
    Optional<Product> findByName(String name);
    //List<Product> findsBycategoryName(String name);
}
