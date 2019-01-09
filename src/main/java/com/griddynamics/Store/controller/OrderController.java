package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.Order;
import com.griddynamics.Store.repository.OrderRepository;
import com.griddynamics.Store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@RestController
@RequestMapping(path="/")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/order/{id}")
    @Transactional
    public Iterable<Order> getOrder(@PathVariable Long id){
        return  orderRepository.findByUserId(id);
    }

    @GetMapping("cancelOrder")
    public void cancelOrder(){
        cartService.emptyCart();
    }
}
