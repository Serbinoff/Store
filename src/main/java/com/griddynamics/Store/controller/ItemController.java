package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.*;
import com.griddynamics.Store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/")
public class ItemController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addItem")
    public String addItemToCart(@RequestBody Item item){
        return cartService.addNewItem(item);
    }

    @DeleteMapping("/removeItem/{id}")
    public int removeItem(@PathVariable Long id){
        return cartService.removeItemFromCart(id);
    }

    @PostMapping("/modifyItem")
    public HttpStatus modifyItem(@RequestBody Item item) {
        return cartService.modifyItemInCart(item);
    }
}
