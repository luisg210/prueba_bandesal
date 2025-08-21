package com.luis.Prueba_bandesal.Services;

import com.luis.Prueba_bandesal.Entity.User;
import com.luis.Prueba_bandesal.Repository.UserRepository;
import com.luis.Prueba_bandesal.Services.Interface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }

    @Override
    public User saveUser(User user) {
        System.out.println(user);
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        System.out.println(username);
        return this.userRepository.findByUsername(username);
    }
}
