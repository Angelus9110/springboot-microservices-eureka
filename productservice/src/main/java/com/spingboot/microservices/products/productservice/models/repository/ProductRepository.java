package com.spingboot.microservices.products.productservice.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.microservices.commons.commonservice.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
