package com.springboot.microservices.items.itemservice.models.service;

import com.springboot.microservices.items.itemservice.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);
}
