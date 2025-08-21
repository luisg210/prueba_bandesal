package com.luis.Prueba_bandesal.Services.Interface;

import com.luis.Prueba_bandesal.Entity.User;

import java.util.Optional;

public interface UserServiceInterface {

    User saveUser(User user);
    Optional<User> findUserByUsername(String username);

}