package com.springboot.microservices.items.itemservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservices.items.itemservice.models.Item;
import com.springboot.microservices.items.itemservice.models.Product;
import com.springboot.microservices.items.itemservice.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @Value("${text.config}")
    private String text;

    @GetMapping("/list")
    public List<Item> list(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/list/{id}/quantity/{quantity}")
    public Item getItem(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    public Item alternativeMethod(Long id, Integer quantity){
        Item item = new Item();
        Product product = new Product();

        item.setQuantity(quantity);
        product.setId(id);
        product.setName("MSI");
        product.setPrice(1650.24);
        item.setProduct(product);
        return item;
    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
        logger.info(text);

        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("port", port);

        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            json.put("Author", env.getProperty("configuration.author.name"));
            json.put("Email", env.getProperty("configuration.author.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
}
