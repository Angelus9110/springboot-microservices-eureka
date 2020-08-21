package com.springboot.microservices.items.itemservice.clients;

import com.springboot.microservices.items.itemservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="product-service")
public interface ClientProductRest {

    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/list/{id}")
    public Product product(@PathVariable Long id);

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product);

    @PutMapping("/edit/{id}")
    public Product edit(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);
}
