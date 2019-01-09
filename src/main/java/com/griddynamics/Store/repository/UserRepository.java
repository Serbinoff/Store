package com.griddynamics.Store.repository;

import com.griddynamics.Store.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}