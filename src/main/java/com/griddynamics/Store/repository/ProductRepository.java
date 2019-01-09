package com.griddynamics.Store.repository;

import com.griddynamics.Store.model.Product;
import com.griddynamics.Store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends CrudRepository<Product, Long> {

}