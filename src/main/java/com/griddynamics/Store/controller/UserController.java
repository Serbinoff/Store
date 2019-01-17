package com.griddynamics.Store.controller;

import com.griddynamics.Store.model.User;
import com.griddynamics.Store.repository.UserRepository;
import com.griddynamics.Store.security.EncryptedPasswordUtils;
import com.griddynamics.Store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/")
public class UserController {

    @Autowired
    private CartService cartService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/newUser")
    public ResponseEntity createUser(@RequestBody User user){
        if(userRepository.findByEmail(user.getEmail()) == null) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(EncryptedPasswordUtils.encryptePassword(user.getPassword()));
            userRepository.save(newUser);
            return ResponseEntity.status(201).body(newUser);
        }
        else return ResponseEntity.status(409).build();
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
        User user = userRepository.findByEmail(request.getUserPrincipal().getName());
        if(BCrypt.checkpw(oldPassword, user.getPassword())){
            user.setPassword(EncryptedPasswordUtils.encryptePassword(newPassword));;
            userRepository.save(user);
            return ResponseEntity.status(201).build();
        }
        else return ResponseEntity.status(409).build();
    }
}
