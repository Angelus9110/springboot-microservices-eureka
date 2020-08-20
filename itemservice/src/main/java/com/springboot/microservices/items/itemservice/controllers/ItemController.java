package com.springboot.microservices.items.itemservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservices.items.itemservice.models.Item;
import com.springboot.microservices.items.itemservice.models.Product;
import com.springboot.microservices.items.itemservice.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/list")
    public List<Item> list(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "altenativeMethod")
    @GetMapping("/list/{id}/quantity/{quantity}")
    public Item getItem(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    public Item altenativeMethod(Long id, Integer quantity){
        Item item = new Item();
        Product product = new Product();

        item.setQuantity(quantity);
        product.setId(id);
        product.setName("MSI");
        product.setPrice(1650.24);
        item.setProduct(product);
        return item;
    }
}
