package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.User;
import com.griddynamics.Store.repository.UserRepository;
import com.griddynamics.Store.service.CartService;
import com.griddynamics.Store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.griddynamics.Store.security.EncryptedPasswordUtils;
import com.griddynamics.Store.security.UserDetailsServiceImpl;
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
    public int createUser(@RequestBody User user){
        user.setId(20L);
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
    public HttpStatus resetPassword(HttpServletRequest request, @RequestParam String oldPassword,
                                    @RequestParam String newPassword) {
        return userService.resetPassword(request, oldPassword, newPassword);
    }
}
