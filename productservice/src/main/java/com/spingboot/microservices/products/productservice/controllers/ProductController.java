package com.spingboot.microservices.products.productservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.spingboot.microservices.products.productservice.models.entity.Product;
import com.spingboot.microservices.products.productservice.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProductController{

	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public List<Product> listProducts(){
		return productService.findAll();
	}
	
	@GetMapping("/list/{id}")
	public Product product(@PathVariable Long id) {
		return productService.findById(id);
	}

}
