package com.springboot.microservices.items.itemservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.microservices.commons.commonservice.models.entity.Product;

public class Item {

    private Product product;
    private Integer quantity;

    public Item() {
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return product.getPrice() * quantity.doubleValue();
    }
}
