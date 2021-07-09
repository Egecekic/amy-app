package com.example.application.services;

import com.example.application.Repositories.ProductRepostories;
import com.example.application.models.Company;
import com.example.application.models.Product;
import com.example.application.models.User;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServicesImp implements ProductServices{

    private final ProductRepostories productRepostories;

    public ProductServicesImp(ProductRepostories productRepostories) {
        this.productRepostories = productRepostories;
    }

    @Override
    public Set<Product> getName() {
        Set<Product> productSet=new HashSet<>();
        return productSet;
    }

    @Override
    public Set<Product> getList() {
        Set<Product> productSet=new HashSet<>();
        //System.out.println(userSet);
        productRepostories.findAll().iterator().forEachRemaining(productSet::add);
        return  productSet;
    }

    @Override
    public void delete(Product product) {
        productRepostories.delete(product);
    }

    @Override
    public Product save(Product product) {
        return productRepostories.save(product);
    }

    @Override
    public Set<Product> getProduct() {
        return null;
    }

    @Override
    public Set<Product> getCompany(long id) {
        Set<Product> productSet=new HashSet<>();
        return productSet;
    }

    @Override
    public Product findById(long id) {
        Optional<Product> product=productRepostories.findAllById(id);
        if (!product.isPresent()){
            throw new RuntimeException("hata");
        }
        return product.get();
    }
}