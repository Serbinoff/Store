package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.*;
import com.griddynamics.Store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity removeItem(@PathVariable Long id){
        return cartService.removeItemFromCart(id);
    }

    @PostMapping("/modifyItem")
    public ResponseEntity modifyItem(@RequestBody Item item) {
        return cartService.modifyItemInCart(item);
    }
}
