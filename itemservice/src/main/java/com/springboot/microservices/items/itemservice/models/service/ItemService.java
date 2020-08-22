package com.springboot.microservices.items.itemservice.models.service;

import com.springboot.microservices.commons.commonservice.models.entity.Product;
import com.springboot.microservices.items.itemservice.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);
    public Product save(Product product);
    public Product update(Product product, Long id);
    public void delete(Long id);
}
