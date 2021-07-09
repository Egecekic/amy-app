package com.example.application.services;

import com.example.application.models.Company;
import com.example.application.models.Product;

import java.util.Set;

public interface ProductServices {
    Set<Product> getName();
    Set<Product> getList();
    void delete(Product product);
    Product save(Product product);
    Set<Product> getProduct();
    Set<Product> getCompany(long id);
    Product findById(long id);
}
