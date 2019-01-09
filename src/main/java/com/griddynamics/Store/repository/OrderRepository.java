package com.griddynamics.Store.repository;

import com.griddynamics.Store.model.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long id);
}