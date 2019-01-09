package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.Product;
import com.griddynamics.Store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
