package com.griddynamics.Store.service;

import com.griddynamics.Store.model.User;
import com.griddynamics.Store.repository.UserRepository;
import com.griddynamics.Store.security.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int createNewUser(User user){
        if(userRepository.findByEmail(user.getEmail()) == null) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(EncryptedPasswordUtils.encryptePassword(user.getPassword()));
            userRepository.save(newUser);
            return HttpStatus.CREATED.value();
        }
        else return HttpStatus.CONFLICT.value();
    }

    public HttpStatus resetPassword(HttpServletRequest request, String oldPass, String newPass) {
        Long userId = userRepository.findByEmail(request.getUserPrincipal().getName()).getId();
        User user = userRepository.findById(userId).get();
        if(BCrypt.checkpw(oldPass, user.getPassword())){
            user.setPassword(EncryptedPasswordUtils.encryptePassword(newPass));;
            userRepository.save(user);
            return HttpStatus.OK;
        }
        else return HttpStatus.CONFLICT;
    }

}
