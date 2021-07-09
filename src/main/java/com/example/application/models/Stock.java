package com.example.application.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @OneToOne
    private Product product;

    private int stockNumber;

    public Stock() {
    }

    public Stock(Product product, int stockNumber) {
        this.product = product;
        this.stockNumber = stockNumber;
    }

    public int getStock() {
        return stockNumber;
    }

    public void setStock(int stock) {
        this.stockNumber = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
