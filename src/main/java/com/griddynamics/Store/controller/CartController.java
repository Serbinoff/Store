package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.Product;
import com.griddynamics.Store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path="/")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public List<Product> getCart(){
        return cartService.getItemsFromCart();
    }

    @GetMapping("/checkOut")
    public String checkOut(HttpServletRequest request){
        return cartService.checkOutCart(request);
    }
}
