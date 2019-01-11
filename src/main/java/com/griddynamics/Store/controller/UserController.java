package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.User;
import com.griddynamics.Store.service.CartService;
import com.griddynamics.Store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/")
public class UserController {

    @Autowired
    private CartService cartService;

    @Autowired
    UserService userService;

    @PostMapping("/newUser")
    public ResponseEntity createUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    public String login() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    @PostMapping("/logout")
    public void logout() {
        cartService.emptyCart();
    }

    @PostMapping("/resetPass")
    public ResponseEntity resetPassword(HttpServletRequest request, @RequestParam String oldPassword,
                                    @RequestParam String newPassword) {
        return userService.resetPassword(request, oldPassword, newPassword);
    }
}
