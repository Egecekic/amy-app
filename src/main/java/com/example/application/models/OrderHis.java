package com.example.application.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrderHis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @OneToOne
    private Product product;
    @OneToOne
    private User user;
    private Long alinanMiktar;

    public OrderHis() {
    }

    public OrderHis(Product product, User user, Long alinanMiktar) {
        this.product = product;
        this.user = user;
        this.alinanMiktar = alinanMiktar;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Long getAlinanMiktar() {
        return alinanMiktar;
    }

    public void setAlinanMiktar(Long alinanMiktar) {
        this.alinanMiktar = alinanMiktar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
