package com.spingboot.microservices.products.productservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.microservices.commons.commonservice.models.entity.Product;
import com.spingboot.microservices.products.productservice.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController{

	@Autowired
	private ProductService productService;

	@Value("${server.port}")
	private Integer port;
	
	@GetMapping("/list")
	public List<Product> listProducts(){
		return productService.findAll().stream().map(product -> {
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/list/{id}")
	public Product product(@PathVariable Long id) throws Exception {
		Product product = productService.findById(id);
		product.setPort(port);
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e){
			e.printStackTrace();
		}*/
		return productService.findById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product){
		return productService.save(product);
	}

	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id){
		Product productEntity = productService.findById(id);
		productEntity.setName(product.getName());
		productEntity.setPrice(product.getPrice());
		return productService.save(productEntity);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		productService.deleteById(id);
	}


}
