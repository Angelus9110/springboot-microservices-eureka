package com.springboot.microservices.items.itemservice.models.service;

import com.springboot.microservices.items.itemservice.clients.ClientProductRest;
import com.springboot.microservices.items.itemservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeignImpl implements ItemService{

    @Autowired
    private ClientProductRest clientProductRest;

    @Override
    public List<Item> findAll() {
        return clientProductRest.list().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(clientProductRest.product(id), quantity);
    }
}
