package com.springboot.microservices.items.itemservice.models.service;

import com.springboot.microservices.items.itemservice.models.Item;
import com.springboot.microservices.items.itemservice.models.Product;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);
    public Product save(Product product);
    public Product update(Product product, Long id);
    public void delete(Long id);
}
