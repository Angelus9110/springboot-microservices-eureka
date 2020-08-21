package com.spingboot.microservices.products.productservice.models.service;

import com.spingboot.microservices.products.productservice.models.entity.Product;

import java.util.List;

public interface ProductService {

	public List<Product> findAll();
	public Product findById(Long id);
	public Product save(Product product);
	public void deleteById(Long id);
}
